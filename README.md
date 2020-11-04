[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# OmniText
Highlights various information (like credit cards, phone numbers, number values) in TextView and adds actions on it.

> Definition of `omni-`: All, Universally

## Example
![OmniText Disabled](readme/omnitext-disabled.png) ![OmniText Enabled](readme/omnitext-enabled.png)

## Installation

1. Put Github username and [token](https://github.com/settings/tokens) with `read:packages` scope into `local.properties` file:

  ```properties
  gpr.user=<github username>
  gpr.key=<token>
  ```

2. Add Github Maven repository to the root `build.gradle` script:

  ```groovy
  Properties properties = new Properties()
  properties.load(project.rootProject.file('local.properties').newDataInputStream())

  allprojects {
      repositories {
          ...
          maven {
              url "https://maven.pkg.github.com/StevenRudenko/OmniText"
              credentials {
                  username properties.getProperty("gpr.user")
                  password properties.getProperty("gpr.key")
              }
          }
      }
  }
  ```

3. Add dependency to app `build.gradle`

  ```groovy
  implementation "com.github.stevenrudenko:omnitext:1.0.0"
  ```

## Usage
Include the `OmniTextView` into layout and use it as standard `TextView`:
```xml
<com.github.stevenrudenko.omnitext.OmniTextView
    android:id="@+id/omniText"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

To subscribe for an omni-actions, add next tines into `Activity` or `Fragment` code:
```kotlin
val omniText = view.findViewById<OmniTextView>(R.id.omniText)
omniText.onOmniAction = { omni ->
    // use omni data here
}
```

## Features
There are next `omni` parsers available:
* Custom text wrapped with tag: `{omni}Text Here{/onmi}`
* Numbers: `12.45`, `0.23`, `244532`
* Phone numbers: `+1-234-567-89-00`
* Credit cards (American Express, MasterCard, Visa, etc.): `371449635398431`, `4012 8888 8888 1881`, `6011-0009-9013-9424`
