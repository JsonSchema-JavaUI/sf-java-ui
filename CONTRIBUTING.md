# Contributing to SF Java UI

:+1::tada: First off, thanks for taking the time to contribute! :tada::+1:

The following is a set of guidelines for contributing to SF Java UI, which is hosted in the [JsonSchema-JavaUI organization](https://github.com/JsonSchema-JavaUI) on GitHub. These are mostly guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a pull request.

## Code of Conduct

This project and everyone participating in it is governed by the [Open source project contributor convenant](http://contributor-covenant.org/). By participating, you are expected to uphold this code. Please report unacceptable behavior to [JsonSchema-JavaUI@github.com](mailto:JsonSchema-JavaUI@github.com).

## I don't want to read this whole thing I just have a question!!!

> **Note:** [Please fill an issue to ask a question.](https://github.com/JsonSchema-JavaUI/sf-java-ui/issues/new).

## What should I know before I get started?

### Json schema form library

Schema Form is inspired by the nice [JSON Form](https://github.com/joshfire/jsonform) library and
aims to be roughly compatible with it, especially its form definition. So what sets Schema Form
apart from JSON Form?

1. Schema Form integrates deeply with AngularJS and uses AngularJS conventions to handle forms.
2. Schema Form uses [tv4](https://github.com/geraintluff/tv4) for validation, making it compatible
   with version 4 of the JSON schema standard.
3. By default, Schema Form generates Bootstrap 3-friendly HTML.

for more details please visit the official [schema form web site](http://schemaform.io/) or the [github page](https://github.com/json-schema-form/angular-schema-form)

### Package Conventions

Thera are a few conventions that have developed over time around packages:

* New layout annotations must be added under [io.asfjava.ui.core.form](https://github.com/JsonSchema-JavaUI/sf-java-ui/tree/master/src/main/java/io/asfjava/ui/core/form)
* New layout generators must be added to [io.asfjava.ui.core.generators](https://github.com/JsonSchema-JavaUI/sf-java-ui/tree/master/src/main/java/io/asfjava/ui/core/generators)

### Design Decisions

When we make a significant decision in how we maintain the project and what we can or cannot support, we will document it in the [design document](https://github.com/JsonSchema-JavaUI/design-decisions). If you have a question around how we do things, check to see if it is documented there. If it is *not* documented there, please [open a new issue](https://github.com/JsonSchema-JavaUI/design-decisions/issues) on [the issue tracker](https://github.com/JsonSchema-JavaUI/design-decisions/issues) and ask your question.

## How Can I Contribute?

### Reporting Bugs

This section guides you through submitting a bug report for SF Java UI. Following these guidelines helps maintainers and the community understand your report :pencil:, reproduce the behavior :computer: :computer:, and find related reports :mag_right:.

Before creating bug reports, please check [this list](#before-submitting-a-bug-report) as you might find out that you don't need to create one. When you are creating a bug report, please [include as many details as possible](#how-do-i-submit-a-good-bug-report). Fill out [the required template](ISSUE_TEMPLATE.md), the information it asks for helps us resolve issues faster.

> **Note:** If you find a **Closed** issue that seems like it is the same thing that you're experiencing, open a new issue and include a link to the original issue in the body of your new one.

#### How Do I Submit A (Good) Bug Report?

Bugs are tracked as [GitHub issues](https://guides.github.com/features/issues/). After you've determined which cause your bug is related to, create an issue on that repository and provide the following information by filling in [the template](ISSUE_TEMPLATE.md).

Explain the problem and include additional details to help maintainers reproduce the problem:

* **Use a clear and descriptive title** for the issue to identify the problem.
* **Describe the exact steps which reproduce the problem** in as many details as possible. 
* **don't just say what you did, but explain how you did it**,
* **Provide specific examples to demonstrate the steps**. Include links to files or GitHub projects, or copy/pasteable snippets, which you use in those examples. If you're providing snippets in the issue, use [Markdown code blocks](https://help.github.com/articles/markdown-basics/#multiple-lines).
* **Describe the behavior you observed after following the steps** and point out what exactly is the problem with that behavior.
* **Explain which behavior you expected to see instead and why.**
* **Include screenshots and animated GIFs** which show you following the described steps and clearly demonstrate the problem.
* **If the problem is related to performance or memory**, include a CPU profile capture
* **If Chrome's developer tools pane is shown without you triggering it**,

### Suggesting Enhancements

This section guides you through submitting an enhancement suggestion for SF Java UI, including completely new features and minor improvements to existing functionality. Following these guidelines helps maintainers and the community understand your suggestion :pencil: and find related suggestions :mag_right:.

Before creating enhancement suggestions, please check [this list](#before-submitting-an-enhancement-suggestion) as you might find out that you don't need to create one. When you are creating an enhancement suggestion, please [include as many details as possible](#how-do-i-submit-a-good-enhancement-suggestion). Fill in [the template](ISSUE_TEMPLATE.md), including the steps that you imagine you would take if the feature you're requesting existed.

#### Before Submitting An Enhancement Suggestion

* **Check if there's already [an opened issue](https://github.com/JsonSchema-JavaUI/sf-java-ui/labels/enhancement) which provides that enhancement.**
* **Perform a [cursory search](https://github.com/JsonSchema-JavaUI/sf-java-ui/issues)** to see if the enhancement has already been suggested. If it has, add a comment to the existing issue instead of opening a new one.

#### How Do I Submit A (Good) Enhancement Suggestion?

Enhancement suggestions are tracked as [GitHub issues](https://guides.github.com/features/issues/). Create an issue on that repository and provide the following information:

* **Use a clear and descriptive title** for the issue to identify the suggestion.
* **Provide a step-by-step description of the suggested enhancement** in as many details as possible.
* **Provide specific examples to demonstrate the steps**. Include copy/pasteable snippets which you use in those examples, as [Markdown code blocks](https://help.github.com/articles/markdown-basics/#multiple-lines).
* **Describe the current behavior** and **explain which behavior you expected to see instead** and why.
* **Explain why this enhancement would be useful** 
* **Specify the version of the [Angular Schema Form](https://github.com/json-schema-form/angular-schema-form).**

### Your First Code Contribution

Unsure where to begin contributing to SF Java UI? You can start by looking `help-wanted` issues:

### Pull Requests

* Fill in [the required template](PULL_REQUEST_TEMPLATE.md)
* Do not include issue numbers in the PR title
* Include screenshots and animated GIFs in your pull request whenever possible.
* Follow the [Java](#java-styleguide) and [Java 8](#java8-styleguide) styleguides.
* Include thoughtfully-worded, well-structured [JUnit](https://github.com/junit-team/junit4) specs. Run them using `mvn test`.
* Document new code based on the [Documentation Styleguide](#documentation-styleguide)
* Avoid platform-dependent code.
## Styleguides

### Git Commit Messages

* Use the present tense ("Add feature" not "Added feature")
* Use the imperative mood
* Limit the first line to 72 characters or less
* Reference issues and pull requests liberally after the first line
* When only changing documentation, include `[ci skip]` in the commit description
* Consider starting the commit message with an applicable emoji:
    * :art: `:art:` when improving the format/structure of the code
    * :racehorse: `:racehorse:` when improving performance
    * :non-potable_water: `:non-potable_water:` when plugging memory leaks
    * :memo: `:memo:` when writing docs
    * :penguin: `:penguin:` when fixing something on Linux
    * :apple: `:apple:` when fixing something on macOS
    * :checkered_flag: `:checkered_flag:` when fixing something on Windows
    * :bug: `:bug:` when fixing a bug
    * :fire: `:fire:` when removing code or files
    * :green_heart: `:green_heart:` when fixing the CI build
    * :white_check_mark: `:white_check_mark:` when adding tests
    * :lock: `:lock:` when dealing with security
    * :arrow_up: `:arrow_up:` when upgrading dependencies
    * :arrow_down: `:arrow_down:` when downgrading dependencies


