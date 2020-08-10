# Publishing artifacts with [Codeartifact](https://aws.amazon.com/codeartifact/) and [Packages](https://github.com/features/packages)

Exploration is and always was part of the [reach-now tech](https://medium.com/reachnow-tech) DNA. While we have an established way to publish code artifacts to an artifacts store, we are keen to learn more. This repository explores how to publish code artifacts to:
- [GitHub Packages](https://github.com/features/packages):
    - [packages/](packages)
- [AWS Codeartifact](https://aws.amazon.com/codeartifact/):
    - [codeartifact/](codeartifact)

We explored the publication usng the main tech stacks we use:
- [NPM](https://www.npmjs.com/)/[Javascript](https://en.wikipedia.org/wiki/JavaScript):
    - [packages/npm/](packages/npm/)
    - [codeartifact/npm/](codeartifact/npm/) 
- [Gradle](https://gradle.org/)/[Kotlin](https://kotlinlang.org/): 
    - [packages/gradle/](packages/gradle/)
    - [codeartifact/gradle/](codeartifact/gradle/) 

You can use this repository to start publishing your own npm/javascript or gradle/kotlin code to GitHub Packages or AWS Codeartifact. In case your favourite tech stack is different, you might be insipired.

The exploration is based on [private repositories](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/about-repositories) because most of our source code lives in private repositories.
