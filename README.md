# Assignment 3 | Bridge Pattern

Name: Melis Anel
Group: SE-2527 
Topic: B - Notifications  
Repository: https://github.com/anelmelis/asik3sdp 
Base commit: 91709fdf03136419899972ff2305486fbe6adcb2


## Topic

This project demonstrates the Bridge Design Pattern using a notification system.
There are two independent hierarchies:

1. Notification types
    - Notification
    - Reminder
    - UrgentAlert

2. Delivery channels
    - Channel
    - EmailChannel
    - SmsChannel
    - PushChannel

The Bridge pattern allows notification types and delivery channels to vary independently.

## Role Map

| Bridge role | Class | File |
|---|---|---|
| Abstraction | Notification | src/Notification.java |
| Refined Abstraction A1 | Reminder | src/Reminder.java |
| Refined Abstraction A2 | UrgentAlert | src/UrgentAlert.java |
| Implementor | Channel | src/Channel.java |
| Implementation I1 | EmailChannel | src/EmailChannel.java |
| Implementation I2 | SmsChannel | src/SmsChannel.java |
| Implementation I3 | PushChannel | src/PushChannel.java |
| Client | Main | src/Main.java |

## Important Bridge Parts

Bridge field:

    protected Channel channel;

Main operation:

    public abstract String execute();

Runtime implementation change:

    public void setImplementation(Channel channel) {
        this.channel = channel;
    }

The T5 check is implemented in src/Main.java.

It changes the implementation on the same Reminder object from EmailChannel to SmsChannel.

The object identity is checked using:

    originalReference == switchTest

The notification ID and message are also checked to confirm that the abstraction state did not change.

## Build and Run

Compile:

    javac --release 17 -encoding UTF-8 -d out "@sources.txt"

Run:

    java -cp out Main --demo

## Expected Demo Results

### T1 - Reminder + EmailChannel

Expected result:

    EMAIL: Reminder: Meeting at 10:00

### T2 - Reminder + SmsChannel

Expected result:

    SMS: Reminder: Meeting at 10:00

### T3 - UrgentAlert + EmailChannel

Expected result:

    EMAIL: URGENT: Server is down

### T4 - UrgentAlert + SmsChannel

Expected result:

    SMS: URGENT: Server is down

### T5 - Runtime implementation switch

The same Reminder object first uses EmailChannel and then changes to SmsChannel.

Expected state:

    sameObject=true
    stateUnchanged=true

Expected results:

    before=EMAIL: Reminder: Submit the assignment
    after=SMS: Reminder: Submit the assignment

### T6 - Reminder + PushChannel

Expected result:

    PUSH: Reminder: Meeting at 10:00

### T7 - UrgentAlert + PushChannel

Expected result:

    PUSH: URGENT: Server is down

Expected summary:

    SUMMARY: 7/7 PASS

## Extension

The base version contained two implementations:

- EmailChannel
- SmsChannel

After the base commit, PushChannel was added as the third implementation.

The base commit is:

    91709fdf03136419899972ff2305486fbe6adcb2

For the extension step, the existing abstraction classes, Channel interface, EmailChannel, and SmsChannel were not changed.
Only the new PushChannel implementation and the demonstration in Main were added.
This shows that the implementation hierarchy can be extended independently without changing the notification hierarchy.

## Bridge vs Adapter

Bridge separates abstraction from implementation so that both sides can change independently.
In this project, the abstraction side contains Notification, Reminder, and UrgentAlert.
The implementation side contains Channel, EmailChannel, SmsChannel, and PushChannel.
Adapter has a different purpose. Adapter is normally used when an existing class has an incompatible interface and needs to work with another interface.
Bridge is used here because the two dimensions of the application were intentionally separated: the notification type and the delivery channel.