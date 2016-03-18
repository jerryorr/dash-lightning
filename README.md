dash-lightning
==============

A demo of using [JMX](http://www.oracle.com/technetwork/articles/java/javamanagement-140525.html), [Spring](https://projects.spring.io/spring-framework/), and [Dash](http://thedash.com) to build an application monitoring dashboard.

Prerequisites
=============
 1. [Git](https://git-scm.com/downloads)
 2. [Maven](https://maven.apache.org/) (comes with many Java IDEs)
 3. [Java 8 SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 4. Create a [Dash](http://thedash.com) account. Note that the **Push to Dash** widgets require a Dash Pro or Dash Business account. A similar effect can be gained by creating REST endpoints similar to those in `TomcatStatsController.java` and using **Fetch from URL** widgets instead.
 
Running the demo
================

 1. Clone the [dash-lightning](https://github.com/jerryorr/dash-lightning) repository on GitHub: `git clone git@github.com:jerryorr/dash-lightning.git`
 2. Create a new [Dash](http://thedash.com) dashboard.
 3. Create the following widgets in your Dash dashboard:
    1. **Custom > Fetch from URL > Custom Speedometer** and name it **Memory Usage**. For **Data URL**, enter `http://[your public IP]:8080/stats/memory/speedometer`. You can get your public IP by [searching google for "my ip address"](https://www.google.com/search?q=my%20ip%20address). You may need to set up port forwarding and open up your firewall, depending on your network/router settings. 
    2. **Custom > Push to Dash > Basic Value** (requires Dash Pro or Business account) and name it **Active Sessions**. Note the **Push URL** for later. 
    3. **Custom > Push to Dash > Custom Chart** (requires Dash Pro or Business account) and name it whatever you want. Note the **Push URL** for later. 
 4. Open `src/main/resources/application.properties` and enter the appropriate **Push URL** above for `dash.chart` and `dash.sessions`. Alternatively, set them as environment variables `DASH_CHART_URL` and `DASH_SESSIONS_URL`.
 5. In the root `dash-lightning` directory, run `mvn spring-boot:run`.
 6. Open [http://localhost:8080/chart](http://localhost:8080/chart) and play with the buttons.
 7. Check out your dashboard and see the magic! The **Memory Usage** widget will update every 30 seconds, the **Active Sessions** and chart widgets will update immediately.
   