GADS
====

GADS stands for Geo Android Debug System.
Written in portable Java, it's a framework born with the target to make the debugging
of Geo-based Android applications easier and faster on AVD.

List of available commands:
-emul "<avdname>"	 start the avd with the specified name <avdname>
-geofix "<address>"	 send a single location fix to the avd
-help
-sendroute <choice> <milliseconds>	 send a route to the avd, the route number <choice>. <milliseconds> is an optional parameter indicting the geopoint sending interval.
-newroute 	 save a new route starting from the start address to the end address inserted after the execution of this command
-navigation <choice>	 start a navigation on the avd with the route number <choice>
-path "<path>"	 set the path of Android sdk tools directory to <path>
-port <port>	 set the port number for the emulator instance to <port>
-next command used during a navigation for sending the next step to the emulator.
-exit command for stopping a navigation.
-quit command for quitting the program, or for stopping a route sending.
