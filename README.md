# sarah-math

The Sarah Math is an app to aid your child in attaining the basics of basic math. Sarah Math manages the configuration and execution of Math Lessons. It concentrates on basic addition, subtraction, multiplication and division. Tha app is intended for children learning simple math i.e. first through fourth ot fifth grades.

Below Find the High Level Design (HLD) of the Sarah Math, depicting the interactions of the various components.


```mermaid

---
title: Lesson Architecture
---

architecture-beta
    group api(cloud)[Sarah Math API]

    service droid(server)[Android] in api
    service sm(server)[Sarah Math] in api
    service config(server)[Configuration Services] in api
    service build(server)[Generate Problems] in api
    service run(server)[Lesson Processor] in api

    sm:T <--> B:config
    config:R <--> L:droid
    droid:L <--> R:config
    sm:L <--> R:build
    sm:R <--> L:run
    run:T <--> B:droid
    droid:B <--> T:run
```

## Table of Contents
- [sarah-math](#sarah-math)
  * [Table of Contents](#table-of-contents)
  * [Development](#development)
    + [Building Sarah Math](#building-sarah-math)
    + [Testing](#testing-sarah-math)
  + [Configuration](#configuration)
  * [Documentation](#documentation)
    + [Open Source References](#open-source-references)
    + [Architecture](#architecture)
  * [Contributing](#contributing)
  * [Contributors](#conributors)

## Development

The Sarah Math was developed in a Linux environment using the Java Language and the Android Studio <version> build environment. 

### Building Sarah Math

#### To build and upload to your connected bench, (in docker, in service directory)

```agsl
gradle clean
gradle build
```

### Testing Sarah Math

Sarah Math is tested in a

## Configuration


## Documentation

Sarah Math documentation is maintained in this document.

![img_2.png](img_2.png)

### Open Source References
[//]: # ([Optional] Add any used open source projects, software or repositories here)

OPTIONAL: Add any used open source projects, software or repositories here

### Architecture


## Contributing

1. `Clone` repository to your machine
1. Create your feature branch (`git checkout -b my-new-feature`)
1. Commit your changes (`git commit -am 'Add some feature'`)
1. Push to the branch (`git push origin my-new-feature`)
1. Create a new Merge Request

## Contributors[Francis Burns]: - Developer, Maintainer

<br><br>
- [Frank Burns]:              - README Content

 <a href="#top">Back to Top</a>