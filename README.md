# Application-State-Backup-and-Restoration-Across-Multiple-Devices

ASBAR (Application State Backup and Restoration Across Multiple Devices) allows user to restore state of linux based system applications and web application as well as allows user to resume program execution of it crashes at any point of time.

- ASBAR restores the application state by first creating backup of state informaion and storing it on Amazon Cloud platform and then using the stored backup to restore state.

- CRIU software tool is used to restore state  of system application and for web application restoration it uses Selenium (software tool) WebDrivers name geckodriver for Firefox and chromedriver for Chrome Browser.

- The uploaded version does not use Cloud storage but stores state information on local machine itself using MySql(because the cloud version requires libraries of size more than 300mb), but the code related to cloud is available in the source files, but is commented.

- System applications restored: These  mainly include command line editors namely nano, vim and pico editors.

- Web applications restored:  Web application restoration includes the data fields which can be restored e.g; Gmail and Facebook signup forms

- Resumption of Program execution: It includes resumption of program execution written in Java, C , C++ and Python programming language.

## Compatibility
Linux (Ubuntu)

## Getting Started

#### Command to get a copy of project on your local machine.

##### On Linux terminal type
$ git clone https://github.com/shubham16kadam/Application-State-Backup-and-Restoration-Across-Multiple-Devices

## Prerequisites
#### Step - 1
###### Install MySQL:

$ sudo apt-get update
$ sudo apt-get install mysql-server

#### Step - 2
###### Java installation

$ sudo add-apt-repository ppa:webupd8team/java

After running the commands above, you should see a prompt to accept the PPA key onto Ubuntu… accept and continue

$ sudo apt update
$ sudo apt install oracle-java8-installer

When you run the commands above you’ll be prompted to access the license terms of the software… accept and continue..

$ sudo apt install oracle-java8-set-default

#### Step- 3
###### CRIU Installation

sudo apt-get install criu

#### Step - 4 
###### Download and install NetBeans IDE

$ wget -c http://download.netbeans.org/netbeans/7.4/final/bundles/netbeans-7.4-linux.sh
$ chmod +x netbeans-8.2-linux.sh 
$ sudo ./netbeans-8.2-linux.sh









