# Publish & Retrieve code artifacts to and from [GitHub Packages](https://github.com/features/packages)

## Setup

### Token

Create a [GitHub personal access token](https://help.github.com/en/github/authenticating-to-github/creating-a-personal-access-token-for-the-command-line#creating-a-token) 
and make sure the following scopes are set:

- `repo`
- `write:packages`
- `read:packages`

_note_: Whenever you change the scope of an existing token, you have to [_reenable SSO_](https://help.github.com/en/github/authenticating-to-github/authorizing-a-personal-access-token-for-use-with-saml-single-sign-on).


## NPM

### Library

See [npm/some-library](npm/some-library) for a sample code of an NPM library.
Create a [GitHub actions workflow](https://help.github.com/en/actions/configuring-and-managing-workflows/configuring-a-workflow) 
and get inspired by [publishing nodejs packages to GitHub Packages](https://help.github.com/en/actions/language-and-framework-guides/publishing-nodejs-packages#publishing-packages-to-github-packages).

Paste the value of the GitHub personal access token in your repository secret - 
`https://github.com/[your_namespace_or_organization]/[your_repo]/settings/secrets`.
Label the secret (e.g. `PCK_MNGR_TOKEN_NPM`) and use the label in [GitHub Action descriptor: publish_npm.yaml](../.github/workflows/publish_npm.yaml#L33):
```yaml
    NODE_AUTH_TOKEN: ${{ secrets.PCK_MNGR_TOKEN_NPM }}
```

Make sure your [package.json](npm/some-library/package.json#L16) contains the [correct target GitHub repository package](https://help.github.com/en/packages/using-github-packages-with-your-projects-ecosystem/configuring-npm-for-use-with-github-packages#publishing-multiple-packages-to-the-same-repository).

[Create an event on the GitHub platform](https://help.github.com/en/actions/reference/events-that-trigger-workflows) e.g. `push` to a branch. The package gets published to `https://github.com/[your_namespace_or_organization]/[your_repo]/packages`.


### Application

```sh
$ cd npm/some-app
```

Install dependency from github package manager scope (details in [Authenticating to GitHub Packages](https://help.github.com/en/packages/using-github-packages-with-your-projects-ecosystem/configuring-npm-for-use-with-github-packages)):
```sh
$ npm login --registry=https://npm.pkg.github.com
> Username: USERNAME
> Password: GITHUB_PERSONAL_ACCESS_TOKEN_VALUE
> Email: PUBLIC-EMAIL-ADDRESS
```

```sh
$ npm i
$ node index.js
ohai: 🔔 🔔 🔔
```

## Gradle

### Library

See [gradle/some-library](gradle/some-library) for a sample Kotlin library.
Create a [GitHub actions workflow](https://help.github.com/en/actions/configuring-and-managing-workflows/configuring-a-workflow) 
and get inspired by [publishing gradle packages to GitHub Packages](https://help.github.com/en/packages/using-github-packages-with-your-projects-ecosystem/configuring-gradle-for-use-with-github-packages#example-using-kotlin-dsl-for-a-single-package-in-the-same-repository).

Paste the value of the GitHub personal access token in your repository secret - 
`https://github.com/[your_namespace_or_organization]/[your_repo]/settings/secrets`.
Label the secret (e.g. `PCK_MNGR_TOKEN_GRADLE`) and use the label in [GitHub Action descriptor: publish_npm.yaml](.github/workflows/publish_jar.yaml#26):
```sh
    ./gradlew -Pgpr.user=[your_user_name] -Pgpr.key=${{ secrets.PCK_MNGR_TOKEN_GRADLE }} publish
```

Make sure your [build.gradle.kts](gradle/some-library/build.gradle.kts#L36) contains the [correct target GitHub repsoitory package](https://help.github.com/en/packages/using-github-packages-with-your-projects-ecosystem/configuring-gradle-for-use-with-github-packages#example-using-kotlin-dsl-for-multiple-packages-in-the-same-repository).

[Create an event on GitHub platform](https://help.github.com/en/actions/reference/events-that-trigger-workflows) e.g. `push` to a branch. The package gets published to `https://github.com/[your_namespace_or_organization]/[your_repo]/packages`.

### Application

Set up [gradle properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties)
to store credentials that allows this repository's code to fetch jar artifacts from 
private GitHub repository packages on your local machine. 

_note_: do not share these gradle properties. 
 
One way of setting up gradle properties is a
`~/.gradle/gradle.properties` file in your home folder. 
Add GitHub credentials like so:

```properties
mavenUser=[Your Github Username]
mavenPassword=[GitHub Personal Access Token]
```

Make sure [GRADLE_USER_HOME](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_environment_variables) is set
and points to `~/.gradle` 


Switch to app folder,
```sh
$ cd gradle/some-app
```

install lib dependency,
```sh
$ ./gradlew install
```

test lib dependency,
```sh
$ ./gradlew test -i
```
check for `testLibRequests` test results,

run the project.
```sh
$ ./gradlew run
```

In another terminal:
```sh
$ curl http://0.0.0.0:8080
hi
$ curl http://0.0.0.0:8080/lib
Kotlin
```