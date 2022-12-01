# QueueClientManagement

This application manages a queue-client system. The main objective is to assigned each client so that the waiting time is minimized. Each queue is implemented as a thread. The architecture of the project is Model-View-Controller.

## Requirements

Running of the program requires an IDE for Java development.
I personally use IntelliJ IDEA Community 2021.2.3

```bash
java --version
java 11.0.12 2021-07-20 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.12+8-LTS-237)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.12+8-LTS-237, mixed mode)
```

## Usage
![image](https://user-images.githubusercontent.com/69772634/205071933-ac66a749-d1f4-4a33-9691-acdda0ca3ee0.png)

Application starts with the window that asks the user for the parameters needed for the simulation:  
    No. of clients  
    No. of queues (threads)  
    The earliest time a client comes and starts waiting to be assigned to a queue  
    The maximum time for the same thing  
    The minimum time a client has to wait in line  
    The maximum time for the same thing  
    The duration of the simulation  

![image](https://user-images.githubusercontent.com/69772634/205073697-7f4d9974-0d91-45c5-94d5-826d5f129ddf.png)

After clicking the generate button, the application will look something like this. Every queue works independently based on the usage of threads.   
The number in the right upper corner is the timer in seconds and the small square in the right lower corner containt all the clients that are waiting to be assigned to a queue.  
The client ID, arrival time and time needed to stay in queue is randomly assigned to him, in suhc a way that the parameters entered previously by the user are satisfied.  

## Output

Because the movement of the GUI can be confusing and the user might not have enough time to analyse in which way were the clients assigned to queues, the application automatically generates a .txt file containing the results of the simulation, as the image shows.
![image](https://user-images.githubusercontent.com/69772634/205073288-b9cd6a39-16d6-4485-8eba-639d3c3aca47.png)

The .txt file prints the clock of the simulation in seconds and which action is performed at which moment.

## Possible further developments

The GUI of hte application can be further updated - I used a basic GUI based on Java Swing.
New parameters can be added to the simulation.
A new entity can be added - the special clients- that would have to be assigned with priority to a queue. That would need some further development

