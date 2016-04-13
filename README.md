# curcon - Currency Converter
[![Build Status](https://travis-ci.org/bosnian/curcon-android.svg?branch=master)](https://travis-ci.org/bosnian/curcon-android)

## About

This is personal class project for course **Human-Computer Interaction (HCI)** on [FIT - Faculty of Information Technology, Mostar ](http://www.fit.ba)

It uses [CurrencyLayer](https://currencylayer.com/) API to make currency conversions. Because it uses free version, calculations for currencies other than USD had to be done manually.

## Setup

### 1. Get API key
Go to [CurrencyLayer](https://currencylayer.com/), register and you will find your key on dashboard.

### 2. Setup key
#### A) Using ENV variable
Set an environment variable *API_ACCESS_KEY* to your key.
#### B) Hardcode
Go to *ServiceGenerator* class and change following line:
```java
private static final String API_ACCESS_KEY = "YOUR_KEY"

```
### 3. Happy playing with it :smile:

<div style="float: right;">
<img src="https://cloud.githubusercontent.com/assets/1209195/14505056/c9ebee60-01b7-11e6-86d6-6a5719dedd20.png" width="300px"/>
<img src="https://cloud.githubusercontent.com/assets/1209195/14505056/c9ebee60-01b7-11e6-86d6-6a5719dedd20.png" width="300px"/>
<div/>


## Copyright & License
 
Project released under the [MIT license](LICENSE.md)
