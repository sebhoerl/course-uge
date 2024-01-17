package org.matsim.uge.course;

import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkWriter;
import org.matsim.contrib.osm.networkReader.SupersonicOsmNetworkReader;
import org.matsim.core.network.algorithms.NetworkCleaner;
import org.matsim.core.utils.geometry.CoordinateTransformation;
import org.matsim.core.utils.geometry.transformations.TransformationFactory;

public class RunConvertNetwork {
	static public void main(String[] args) {
		String inputFile = "study_area.pbf";
		String outputFile = "study_area.network.xml";
		String crs = "EPSG:2154";

		CoordinateTransformation coordinateTransformation = TransformationFactory
				.getCoordinateTransformation(TransformationFactory.WGS84, crs);

		Network network = new SupersonicOsmNetworkReader.Builder().setCoordinateTransformation(coordinateTransformation)
				.build().read(inputFile);
		
		new NetworkCleaner().run(network);

		new NetworkWriter(network).write(outputFile);
	}
}
