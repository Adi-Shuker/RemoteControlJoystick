# Remote Control Joystick

# About The Project

Remote Control Joystick is an Android application that allows the user to control and dirige an airplane on a flight simulator via an Android phone or tablet.<br>
The remote allows, among other things, the control of the speed, the altitude, the direction, the position of the ailerons etc. <br>
It also allows the user to see and move the airplane in different views.<br>

Remote's features: <br>
• joystick to control the position of the ailerons (X axis) and the elevator (Y axis) <br>
• seek bars to change the speed (throttle) and the direction of the airplane (rudder) <br>

To run it, the user has to enter the IP address and port of the device on which the simulator is running.<br>
To connect to the simulator and start flying, he has to click on the button *Connect*.<br> 
<br>
 ![alt tag](https://user-images.githubusercontent.com/81378726/123151129-d0868c80-d46b-11eb-82b0-f5d9f3e2d240.PNG)
<br>


# Getting Started

# Prerequisites

1) Install the flight simulator - FlightGear(https://www.flightgear.org/) version 2020.3.8. (for all OS)
2) Open the FlightGear and in the Additional Settings write the lignes below and than press "fly" : <br>
--telnet=socket,in,10,127.0.0.1,6400,tcp  <br>


# Download

Options to download the app: 
- Clone the repository https://github.com/Adi-Shuker/RemoteControlJoystick.
- Download the zip.


# Usage

Run the FlightGear and the Remote Control Joystick app. <br>
In the top of the screen enter the IP address and port of the device which runs the simulator. <br>
To connect to the simulator and start flying  press *Connect*.<br>
Good fly! <br>
<br>
 ![alt tag](https://user-images.githubusercontent.com/81378726/123151990-bdc08780-d46c-11eb-9910-ffcf7ba14539.PNG)
<br>

<br>

# Code Design and UML:
<br>


 ![alt tag](https://user-images.githubusercontent.com/81378726/123170755-8f01db80-d483-11eb-8081-d3ab7bef8b71.PNG)
<br>

The architecture of the app is based on the *MVVM* architectural pattern. <br>
Model–view–viewmodel (MVVM) is a software architectural pattern that facilitates the separation of the development of the graphical user interface (the view)<br>
The view is the appearance of what the user sees on the screen. It displays a representation of the model and receives the user's interaction with the view (mouse clicks,
keyboard input, screen tap gestures, etc.), and it forwards the handling of these to the view model via the data binding (properties, event callbacks) that is defined to link the view and view model.<br>
The view model is an abstraction of the view exposing public properties and commands. The view directly binds to properties on the view model to send and receive updates. <br>
The Model interacts with the FlightGear app via TCP connection like a client-server.

# Video Explanation 

https://youtu.be/230t_UPn8s0

# Contributors
This program was developed by Adi-Shuker, Shana026
