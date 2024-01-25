package org.matsim.uge.course;

import org.matsim.api.core.v01.network.Network;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;
import org.matsim.utils.gis.matsim2esri.network.FeatureGeneratorBuilderImpl;
import org.matsim.utils.gis.matsim2esri.network.LanesBasedWidthCalculator;
import org.matsim.utils.gis.matsim2esri.network.LineStringBasedFeatureGenerator;
import org.matsim.utils.gis.matsim2esri.network.Links2ESRIShape;

public class RunExportNetwork {
	static public void main(String[] args) {
		String inputPath = "study_area.network.xml";
		String outputPath = "matsim_network.shp";

		Network network = NetworkUtils.createNetwork();
		new MatsimNetworkReader(network).readFile(inputPath);

		FeatureGeneratorBuilderImpl builder = new FeatureGeneratorBuilderImpl(network, "EPSG:2154");
		builder.setFeatureGeneratorPrototype(LineStringBasedFeatureGenerator.class);
		builder.setWidthCoefficient(0.5);
		builder.setWidthCalculatorPrototype(LanesBasedWidthCalculator.class);
		new Links2ESRIShape(network, outputPath, builder).write();
	}
}
