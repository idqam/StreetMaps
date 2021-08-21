Owen Ariza Villareal


minTreeCalc.java: 

	This file runs the algorithm used to find the minimum weight spanning tree. It uses a priority queue and a graph. 

guiGraph.java:

	This file creates the gui for the given text file whenever the command "-show" is included. The map is drawn in black. The shortest path (djisktra) is always in red color. While the minimum weight spanning tree (the meridian) is in blue. 

Djisktra.java:

	Implements Djisktra's algorithm. If the starting node or the ending node do not exist then it prints that they do not exist respectively. Else it prints it out in the format: end --> ..... --> start --> where the extra arrow is well just a small bug (feature).  The elapsed time is also printed in ms. 

Node, Graph, Edge:

	Are just the standard node, graph, edge classes respectively. 

Main.java:

	is where the program is ran. It determines based on how many arguments are passed and the arg length to either show or just print the meridian. 

Colors: 
The maps are drawn in black. Djisktra's shortest path is colored red and the meridian(s) is in blue. 





					How it runs:

Compile all the java files
Type in if you want just the map and shortest path via djiskstra:
	 java Main.java textfilename.txt -show intersection1 intersection2 
Else if for the map with the meridian via min weight span tree: 
	java Main.java textfilename.txt -show intersection1 intersection2 -meridian
Else just the meridian and shortest path printed with no map drawn:
	java Main.java textfilename.txt intersection1 intersection2 -meridian