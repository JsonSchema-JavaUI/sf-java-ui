# SF JAVA UI
Schema Form Java based library allow developers to define schema and form using field annotations.

[![Build Status](https://travis-ci.org/JsonSchema-JavaUI/sf-java-ui.svg?branch=master)](https://travis-ci.org/JsonSchema-JavaUI/sf-java-ui)
[![Quality Gate](https://sonarcloud.io/api/badges/gate?key=io.sfjava.ui:sf-java-ui)](https://sonarcloud.io/dashboard?id=io.sfjava.ui%3Asf-java-ui)
[![Technical debt ratio](https://sonarcloud.io/api/badges/measure?key=io.sfjava.ui:sf-java-ui&metric=sqale_debt_ratio)](https://sonarcloud.io/dashboard?id=io.sfjava.ui%3Asf-java-ui)
[![Coverage Status](https://coveralls.io/repos/github/JsonSchema-JavaUI/sf-java-ui/badge.svg?branch=master)](https://coveralls.io/github/JsonSchema-JavaUI/sf-java-ui?branch=master)
[ ![Download](https://api.bintray.com/packages/jsonschema-javaui/SF-Java-UI/sf-java-ui/images/download.svg) ](https://bintray.com/jsonschema-javaui/SF-Java-UI/sf-java-ui/_latestVersion)

The SF Java UI library is a server side extension for the [Json Schema Form](https://github.com/json-schema-form) library. It make your development easier, you will not care about how to define your schema and your screen form definition. Just annotate your fields, and get your screen.

If you use SF Java UI in your project/company please let us know.

## Run the Demo
The attached demo application is a Spring Boot + Angular web application. 
To run the app please go to the demo directory and run the commands below:

```bash
bower install
```
This will install the basic web dependencies.

```bash
mvn spring-boot:run
```
This will run the application

## Quick start
Using SF Java UI is simple. Follow the steps below to get your first screen.

### Backend

First, add the SF Java UI library to your java web project:

```xml
<dependency>
  <groupId>io.sfjava.ui</groupId>
  <artifactId>sf-java-ui</artifactId>
  <version>RELEASE</version>
</dependency>
```
Add the oss repository:
```xml
The official version Not yet deployed
```

Create a new Rest Web Service. (example using spring)

```Java
import com.fasterxml.jackson.databind.JsonMappingException;

import io.asfjava.ui.core.schema.UiFormSchemaGenerator;
import io.asfjava.ui.dto.UiForm;

/**
 * REST controller for managing Ui.
 * The rest controller handle the JsonSchemaForm request. It call the SF JAVA UI library to 
 * generate the json schema and the form definition
 */
@RestController
@RequestMapping("/api")
public class DemoRestService {

	public DemoRestService() {
	}

	@RequestMapping("/ui")
	public UiForm renderUI() throws JsonMappingException {
		return UiFormSchemaGenerator.get().generate(DemoForm.class);
	}
}
```
Then create the DemoForm class:

```Java
import java.io.Serializable;

import io.asfjava.ui.core.form.ComboBox;
import io.asfjava.ui.core.form.TextArea;
import io.asfjava.ui.core.form.TextField;

/**
 * Pojo represent the view to display. It must implement java.io.Serializable.
 * We are using SF JAVA UI Annotations to define the different fields layout
 */
public class DemoForm implements Serializable {

	@TextField(title = "First Name", placeHolder = "Your first name", description="This is a description for your first name field")
	private String firstName;

	@TextField(title = "Last Name", placeHolder = "Your last name")
	private String lastName;

	@ComboBox(title = "Gender", values = { "Male", "Female" })
	private String gender;

	@TextArea(title = "Address", placeHolder = "Fill your address please", description = "This is a textarea")
	private String address;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
```
### Front

First, add to your java web project the required client side library in order to use [json schema form](https://github.com/json-schema-form). 
After, inject the json schema form into your html page and add your Js controller to call the backend.

For the example below we will use [Angular schema form](https://github.com/json-schema-form/angular-schema-form). You can follow the official [documentation](https://github.com/json-schema-form/angular-schema-form#documentation).

Put the code below into your view.html:

```HTML
<div ng-controller="FormController">
    <form sf-schema="schema" sf-form="form" sf-model="model"></form>
</div>
```
Add the angular controller below:

```javascript
angular.module('MyModule', ["schemaForm"]).controller('FormController', function($scope,$http) {
  $http.get('/api/ui').then(successCallback, errorCallback);
  function successCallback(response){
    $scope.schema = response.data.schema;
    $scope.form = response.data.form;
    $scope.model = {};
  }
  function errorCallback(error){
    //error code
  }
});
```

Run your app and go to your screen. Enjoy :)

## Reporting Issue
SF Java UI uses GitHub’s integrated issue tracking system to record bugs and feature requests. If you want to raise an issue, please follow the recommendations below:

  - Before you log a bug, please [search the issue tracker](https://github.com/JsonSchema-JavaUI/sf-java-ui/search?type=Issues) to see if someone has already reported the problem.

  - If the issue doesn’t already exist, [create a new issue](https://github.com/JsonSchema-JavaUI/sf-java-ui/issues/new).

  - Please provide as much information as possible with the issue report, we like to know the version of SF Java UI that you are using and the Json Schema Form version as well as your Operating System and JVM version.

  - If you need to paste code, or include a stack trace use Markdown \``` escapes before and after your text.

## Contributing
Contributions are welcome! Please see [Contributing.md](CONTRIBUTING.md) for more info.
