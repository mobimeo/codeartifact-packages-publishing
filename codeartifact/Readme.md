# Publish & Retrieve code artifacts to and from AWS CodeArtifact

## Create

_Note_: No cloudformation support as of 2020-06-11

### Domain

```
aws codeartifact create-domain --domain some-domain
```

### Repository

```
aws codeartifact create-repository \
  --domain some-domain \
  --repository some-repository
```

## NPM

### Retrieve Token

The token is used to authorize access to the repos. It is valid for 12h by default and you can specify an AWS account via `--domain-owner`.

```
export CODEARTIFACT_AUTH_TOKEN=$(aws codeartifact get-authorization-token \
  --domain some-domain \
  --domain some-repository \
  --query authorizationToken \
  --output text)
```

### Library

Create `.npmrc` file for your GitHub namespace/organisation (eg.g. [reachnow](https://github.com/reach-now/)). Modules will be pushed and pulled to the repo when using this namespace/organisation.

```
cd npm/some-library
export REPOSITORY_ENDPOINT=$(aws codeartifact get-repository-endpoint \
  --domain some-domain \
  --repository some-repositiory \
  --format npm \
  --query repositoryEndpoint \
  --output text)
cat << EOF > .npmrc
@reach-now:registry=$REPOSITORY_ENDPOINT
${REPOSITORY_ENDPOINT#https:}:always-auth=true
${REPOSITORY_ENDPOINT#https:}:_authToken=\${CODEARTIFACT_AUTH_TOKEN}
EOF
```

Build and publish module:

```
npm i
npm publish
```

### Application

```
cd npm/some-app
cp ../some-library/.npmrc .
```

Install dependency from namespace/organisation scope:

```
npm i
node index.js
ohai: 🔔 🔔 🔔
```

## Gradle

### Library

Add your repository url to `gradle.properties` as a publish target.

```
cd gradle/some-library
export REPOSITORY_ENDPOINT=$(aws codeartifact get-repository-endpoint \
  --domain some-domain \
  --repository some-repositiory \
  --format maven \
  --query repositoryEndpoint \
  --output text)
echo "reachnowRepoUrl=$REPOSITORY_ENDPOINT" > gradle.properties
```

Build and publish

```
gradle clean build publish
```

### Application

```
cd gradle/some-app
export REPOSITORY_ENDPOINT=$(aws codeartifact get-repository-endpoint \
  --domain some-domain \
  --repository some-repositiory \
  --format maven \
  --query repositoryEndpoint \
  --output text)
echo "reachnowRepoUrl=$REPOSITORY_ENDPOINT" >> gradle.properties
```

Install dependency from _[Domain.GithubNamespace/Organization].some-library_ (e.g. `com.reach-now.some-library`) and run app:

```
gradle clean build jar
java -jar build/libs/some-app-0.1-all.jar
```

The endpoint returns a string from `some-library`:

```
curl localhost:8080/hello
Kotlin
```
