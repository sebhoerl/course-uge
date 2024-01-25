package org.matsim.uge.course;

import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.population.Leg;
import org.matsim.api.core.v01.population.Person;
import org.matsim.api.core.v01.population.Plan;
import org.matsim.contrib.drt.optimizer.insertion.extensive.ExtensiveInsertionSearchParams;
import org.matsim.contrib.drt.run.DrtConfigGroup;
import org.matsim.contrib.drt.run.DrtConfigGroup.OperationalScheme;
import org.matsim.contrib.drt.run.DrtControlerCreator;
import org.matsim.contrib.drt.run.MultiModeDrtConfigGroup;
import org.matsim.contrib.drt.run.MultiModeDrtModule;
import org.matsim.contrib.dvrp.run.DvrpConfigGroup;
import org.matsim.contrib.dvrp.run.DvrpModule;
import org.matsim.contrib.dvrp.run.DvrpQSimComponents;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup.ActivityParams;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup.ModeParams;
import org.matsim.core.config.groups.QSimConfigGroup.StarttimeInterpretation;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.router.TripStructureUtils;
import org.matsim.core.scenario.ScenarioUtils;

public class RunOnDemand {
	static public void main(String[] args) {
		Config config = ConfigUtils.createConfig();

		DvrpConfigGroup dvrpConfigGroup = new DvrpConfigGroup();
		config.addModule(dvrpConfigGroup);

		MultiModeDrtConfigGroup mmDrtConfigGroup = new MultiModeDrtConfigGroup();
		config.addModule(mmDrtConfigGroup);

		DrtConfigGroup drtConfigGroup = new DrtConfigGroup();
		mmDrtConfigGroup.addParameterSet(drtConfigGroup);

		drtConfigGroup.mode = "drt";
		drtConfigGroup.stopDuration = 60.0;
		drtConfigGroup.maxWaitTime = 600.0;
		drtConfigGroup.maxTravelTimeBeta = 600.0;
		drtConfigGroup.maxTravelTimeAlpha = 1.5;
		drtConfigGroup.operationalScheme = OperationalScheme.door2door;

		ExtensiveInsertionSearchParams searchParams = new ExtensiveInsertionSearchParams();
		drtConfigGroup.addParameterSet(searchParams);

		drtConfigGroup.vehiclesFile = "drt_vehicles_20.xml";

		config.controler().setOutputDirectory("veh20_output");
		config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);

		ActivityParams genericParams = new ActivityParams("generic");
		genericParams.setScoringThisActivityAtAll(false);
		config.planCalcScore().addActivityParams(genericParams);

		ModeParams drtModeParams = new ModeParams("drt");
		config.planCalcScore().addModeParams(drtModeParams);

		config.plans().setInputFile("plans.xml");
		config.network().setInputFile("study_area.network.xml");

		config.controler().setLastIteration(0);

		config.qsim().setStartTime(0.0);
		config.qsim().setSimStarttimeInterpretation(StarttimeInterpretation.onlyUseStarttime);
		config.qsim().setFlowCapFactor(1e9);
		config.qsim().setStorageCapFactor(1e9);

		Scenario scenario = DrtControlerCreator.createScenarioWithDrtRouteFactory(config);
		ScenarioUtils.loadScenario(scenario);

		for (Person person : scenario.getPopulation().getPersons().values()) {
			for (Plan plan : person.getPlans()) {
				for (Leg leg : TripStructureUtils.getLegs(plan)) {
					leg.setMode("drt");
				}
			}
		}

		Controler controller = new Controler(scenario);

		controller.addOverridingModule(new DvrpModule());
		controller.addOverridingModule(new MultiModeDrtModule());
		controller.configureQSimComponents(DvrpQSimComponents.activateAllModes(mmDrtConfigGroup));

		controller.run();
	}
}
