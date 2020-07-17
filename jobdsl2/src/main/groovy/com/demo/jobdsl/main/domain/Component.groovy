package com.demo.jobdsl.main.domain

class Component {
    public String image
    public String imageName
    public String name
    public String location
//    public ComponentType type
    public Boolean serviceOverrides
    public String triggerCron
//    public BuildTrigger trigger
    public Set<String> triggerEnv
    public Integer quietPeriod // Defines a timespan (in seconds) to wait for additional events (pushes, check-ins) before triggering a build.
    public String deployTimeout
    public Boolean deploySkipVerification // Skips post-deployment verification when using direct deployment (kubectl apply)
    public String displayName
    public Map extensions
//    public TestFramework testFramework
}
