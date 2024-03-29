# Quarkus playground

This repository contains **code** written following tutorials and developing projects while learning about [Quarkus](https://quarkus.io/).

## Repository Structure

There's a folder in the root of this repository for each tutorial followed. Each of those folders contains one or more projects that can be imported to any [IDE](https://en.wikipedia.org/wiki/Integrated_development_environment).

## Code Tutorials and Courses

### Getting Started with Apache Kafka in your Quarkus application

The code written following the [Getting Started with Apache Kafka in your Quarkus application](https://quarkus.io/blog/getting-started-kafka/) tutorial is stored in the folder [`getting-started-kafka`](./getting-started-kafka) of this repository.

To stop the Kafka broker deployed to test the tutorial's code, execute the below commands on the project's root folder:

    docker-compose down
    docker-compose rm

### Getting started to Smallrye Reactive Messaging with Apache Kafka

The code written following the [Getting started to Smallrye Reactive Messaging with Apache Kafka](https://quarkus.io/guides/kafka-reactive-getting-started) 
tutorial is stored in the folder [`kafka-quickstart`](./kafka-quickstart) of this repository. This folder contains the following projects:

+ [kafka-quickstart-processor](./kafka-quickstart/kafka-quickstart-processor);
+ [ kafka-quickstart-producer](./kafka-quickstart/kafka-quickstart-producer);

### Collect Metrics using Micrometer

The code written following the [Collect Metrics using Micrometer](https://quarkus.io/guides/telemetry-micrometer-tutorial) tutorial is stored in the folder [`micrometer-quickstart`](./micrometer-quickstart) of this repository.
