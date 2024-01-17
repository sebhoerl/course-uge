package org.matsim.uge.course;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.config.groups.PlanCalcScoreConfigGroup.ActivityParams;
import org.matsim.core.config.groups.StrategyConfigGroup.StrategySettings;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.scenario.ScenarioUtils;

public class RunBaseline {
	static public void main(String[] args) {
		Config config = ConfigUtils.createConfig();
		
		config.controler().setOutputDirectory("baseline_output");
		config.controler().setOverwriteFileSetting(OverwriteFileSetting.deleteDirectoryIfExists);

		ActivityParams genericParams = new ActivityParams("generic");
		genericParams.setScoringThisActivityAtAll(false);
		config.planCalcScore().addActivityParams(genericParams);

		config.plans().setInputFile("plans.xml");
		config.network().setInputFile("study_area.network.xml");
		
		config.controler().setLastIteration(0);
		
		Scenario scenario = ScenarioUtils.createScenario(config);
		ScenarioUtils.loadScenario(scenario);

		Controler controller = new Controler(scenario);
		controller.run();
	}
}
