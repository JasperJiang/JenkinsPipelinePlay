package com.demo.jobdsl.main.domain

class Repository {

    public String path
    public String name
    public String trunkBranch
    public String gitCredentialsId
    public String gitRepositoryOwner
    public String gitRepositorySchema
    public String gitRepositoryHost
    public String dockerRegistry
    public String dockerRegistryCredentialsId
    public Boolean googleChatNotificationsEnabled
    public String googleChatNotificationsCredentialsId
    public List<Component> components

}
