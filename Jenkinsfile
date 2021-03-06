#!groovy
import org.boon.Boon;
import groovy.json.*;

properties([
        [$class: 'BuildDiscarderProperty', strategy:[$class: 'LogRotator', numToKeepStr: '200']],
        //pipelineTriggers([cron(env.BRANCH_NAME == 'master' ? 'H */3 * * *' : '')]),
        //pipelineTriggers([cron(env.BRANCH_NAME == 'master' ? 'H H(0-2) * * *' : '')]),
        parameters([

                 choice(name: 'HELLO', choices: 'CI\nDEV\nRC\nNI', description: 'build type'),


                extendedChoice (bindings: '', description: '', groovyClasspath: '', groovyScript: '''
                import org.boon.Boon;

                def jsonEditorOptions = Boon.fromJson(/{

                        disable_edit_json: true,

                        disable_properties: true,

                        no_additional_properties: true,

                        disable_collapse: false,

                        disable_array_add: true,

                        disable_array_delete: true,

                        disable_array_reorder: true,

                        theme: "bootstrap3",

                        iconlib:"fontawesome4",

                        schema: {

                    "title": "Build Parameters",

                    "type": "object",

                    "required": [

                        "ENTITY_ENGINE_NAME",

                        "LIB_VERSION",

                        "BUILD_TYPE",

                        "ENTITY_EMBEDDED_VERSION",

                         "FORCE_BUILD",
                          "REFRESH_BUILD",
                           "releaseNotes",
                           "onboard",
                           "cloud"





                    ],

                    "properties": {

                        "ENTITY_EMBEDDED_VERSION": {

                            "type": "string",

                            "description": "Embedded version for entity build"

                        },

                        "ENTITY_ENGINE_NAME": {

                            "type": "string",

                            "description": "Engine name for entity build",

                            "default": "entity-search-embedded-lite"

                        },


                           "FORCE_BUILD": {

                                                    "type": "boolean",

                                                    "format": "checkbox",

                                                    "description": "Force build the app and service or not."

                                                },

                        "LIB_VERSION": {

                            "type": "string",

                            "description": "You can input the Jenkins shared library version, the repository link:",

                            "default": "release1.0"

                        },

                        "BUILD_TYPE": {

                            "type": "string",

                            "enum": [

                                "CI",

                                "DEV",

                                "NI",

                                "RC"

                            ]

                        },



                        "REFRESH_BUILD": {

                            "type": "boolean",

                            "format": "checkbox",

                            "description": "Do NOT do anything only refresh current script and skip all steps."

                        },

                        "releaseNotes": {

                            "title": "Release Notes",

                            "type": "object",

                             "required": [

                                                    "ONBOARD_JIRA_QUERY",

                                                    "CLOUD_JIRA_QUERY"





                                                ],

                            "properties": {

                                "ONBOARD_JIRA_QUERY": {

                                    "type": "string",

                                    "description": "Onboard search engine release - jira query filter",

                                    "default": "fixVersion in (Sprint119) AND project = SemanticSearch and issuetype in (Epic, Story, Bug) and resolution !=Unresolved and component not in (SearchQA, SearchQuality, SQ-Core, SQ-TestGeneration) order BY type DESC"

                                },

                                "CLOUD_JIRA_QUERY": {

                                    "type": "string",

                                    "description": "Cloud entity service release - jira query filter",

                                    "default": "fixVersion in (Sprint119_Cloud) AND project = SemanticSearch AND ('Product Release' in ('Denali Product 2', 'Denali Product 1 2017 MY', 'Denali Product 1 2018 MY', 'Denali Product 1 2019 MY', 'Denali Product 1 2020 MY', 'Denali Product 1 2021 MY') OR 'Product Release' IS EMPTY) AND 'Search Mode' in ('Hybrid','Cloud_Only') ORDER BY type DESC"

                                }

                            }

                        },

                         "cloud": {

                                                    "title": "Cloud Settings",

                                                    "type": "object",

                                                      "required": [

                                                                                                                                    "CLOUD_BUILD_PRODUCT",

                                                                                                                                    "CLOUD_BUILD_REGIONS",

                                                                                                                                      "CLOUD_DATA_LOCATION"





                                                                                                                                ],

                                                    "properties": {

                                                        "CLOUD_BUILD_PRODUCT": {

                                                            "type": "string",

                                                            "description": "build product",

                                                            "default" : "Sync4",

                                                            "enum": [

                                                                "Denali",

                                                                "Sync4",

                                                                "Gen3"

                                                            ]

                                                        },

                                                        "CLOUD_BUILD_REGIONS": {

                                                            "format": "checkbox",

                                                            "type": "array",

                                                            "default": "na",

                                                            "items": {

                                                                "type": "string",

                                                                "enum": [

                                                                    "af",

                                                                    "anz",

                                                                    "cn",

                                                                    "eu",

                                                                    "il",

                                                                    "kr",

                                                                    "mea",

                                                                    "na",

                                                                    "sa",

                                                                    "sea",

                                                                    "tr",

                                                                    "tw"

                                                                ]

                                                            },

                                                            "uniqueItems": true

                                                        },

                                                        "CLOUD_DATA_LOCATION": {

                                                            "format": "textarea",

                                                            "type": "string",

                                                            "description": "Index data location, please use comma to separate multiple locations.",

                                                            "default": ""

                                                        }

                                                    }

                                                },



                        "onboard": {

                            "title": "Onboard Settings",

                            "type": "object",

                             "required": [

                                                                                "ONBOARD_BUILD_PRODUCT",

                                                                                "ONBOARD_BUILD_REGIONS",

                                                                                 "ONBOARD_DATA_LOCATION"





                                                                            ],

                            "properties": {

                                "ONBOARD_BUILD_PRODUCT": {

                                    "type": "string",

                                    "description": "build product",

                                    "enum": [

                                        "Denali",

                                        "Sync4",

                                        "Gen3"

                                    ]

                                },

                                "ONBOARD_BUILD_REGIONS": {

                                    "format": "checkbox",

                                    "type": "array",

                                    "items": {

                                        "type": "string",

                                        "enum": [

                                            "af",

                                            "anz",

                                            "cn",

                                            "eu",

                                            "il",

                                            "kr",

                                            "mea",

                                            "na",

                                            "sa",

                                            "sea",

                                            "tr",

                                            "tw"

                                        ]

                                    },

                                    "uniqueItems": true

                                },

                                "ONBOARD_DATA_LOCATION": {

                                    "format": "textarea",

                                    "type": "string",

                                    "description": "Index data location, please use comma to separate multiple locations.",

                                    "default": ""

                                }

                            }

                        },

                         "DESCRIPTION": {

                                                    "type": "string",

                                                    "description": "Any descriptions showing in release note",

                                                    "default": "NA"

                                                }

                                            }

                                        },



                       startval: {

                         color :"red"

                       }

                }/);

   ''', multiSelectDelimiter: ',', name: 'Policy', quoteValue: false, saveJSONParameterToFile: false, type: 'PT_JSON', visibleItemCount: 5)
        ])
])



import org.boon.Boon;




@NonCPS
def Map getUserData(String json) {
    def jsonSlurper = new JsonSlurper()
    def outcome = jsonSlurper.parseText(json)
    assert outcome instanceof Map


    def entityEmbeddedVersion = outcome['ENTITY_EMBEDDED_VERSION']
    def entityEngineName = outcome['ENTITY_ENGINE_NAME']


    def forceBuild = outcome['FORCE_BUILD']
    def libVersion = outcome['LIB_VERSION']
    def refreshBuild = outcome['REFRESH_BUILD']


    def cloudParams = outcome['cloud']

    def onBoardParams = outcome['onboard']

    def onBoardBuildProduct = onBoardParams['ONBOARD_BUILD_PRODUCT']
    def onBoardBuildRegions = onBoardParams['ONBOARD_BUILD_REGIONS']
    def onBoardDataLocation = onBoardParams['ONBOARD_DATA_LOCATION']

    def  cloudBuildProduct = onBoardParams['CLOUD_BUILD_PRODUCT']
    def cloudBuildRegions = onBoardParams['CLOUD_BUILD_REGIONS']
    def cloudDataLocation = onBoardParams['CLOUD_DATA_LOCATION']

    def onBoardJIRAQuery = outcome['releaseNotes']['ONBOARD_JIRA_QUERY']
    def cloudJIRAQuery = outcome['releaseNotes']['CLOUD_JIRA_QUERY']


    //println("buildType: ${buildType}")
    println("entityEmbeddedVersion: ${entityEmbeddedVersion}")
    println("entityEngineName: ${entityEngineName}")
    println("forceBuild: ${forceBuild}")

    println("libVersion: ${libVersion}")
    println("refreshBuild: ${refreshBuild}")



    return outcome



}


def personName =  getUserData("${Policy}")

def hell = personName['FORCE_BUILD']



println ("VINEEL TESTING HIS CODE ${hell}")

/**
 *
 * {
 * "BUILD_TYPE": "CI",
 * "ENTITY_EMBEDDED_VERSION": "wedewfwefwef",
 * "ENTITY_ENGINE_NAME": "entity-search-embedded-lite",
 * "FORCE_BUILD": false,
 * "LIB_VERSION": "release1.0",
 * "REFRESH_BUILD": false,
 * -"cloud": {
 * "CLOUD_BUILD_PRODUCT": "Sync4",
 * +"CLOUD_BUILD_REGIONS": [ … ],
 * "CLOUD_DATA_LOCATION": ""
 * },
 * -"onboard": {
 * "ONBOARD_BUILD_PRODUCT": "Denali",
 * "ONBOARD_BUILD_REGIONS": [ ],
 * "ONBOARD_DATA_LOCATION": ""* },
 * -"releaseNotes": {
 * "CLOUD_JIRA_QUERY": "fixVersion in (Sprint119_Cloud) AND project = SemanticSearch AND ('Product Release' in ('Denali Product 2', 'Denali Product 1 2017 MY', 'Denali Product 1 2018 MY', 'Denali Product 1 2019 MY', 'Denali Product 1 2020 MY', 'Denali Product 1 2021 MY') OR 'Product Release' IS EMPTY) AND 'Search Mode' in ('Hybrid','Cloud_Only') ORDER BY type DESC",
 * "ONBOARD_JIRA_QUERY": "fixVersion in (Sprint119) AND project = SemanticSearch and issuetype in (Epic, Story, Bug) and resolution !=Unresolved and component not in (SearchQA, SearchQuality, SQ-Core, SQ-TestGeneration) order BY type DESC"
 * }* }
 *
 *
 */





/**
node {

    print("JSON SLURPER..........")



     def hello  = "${Policy}"


    //ObjectMapper mapper = JsonFactory.create();


     getUserData("$hello")






    println("****************")
    println("HELLISH HELLLLLL")
    //println("${buildType}")
    println("HELLISH HELLLLLL DONE")
    println("****************")
    //sh "echo ${ENTITY_EMBEDDED_VERSION}"
    // Make the output directory.
    sh "mkdir -p output"

    // Write an useful file, which is needed to be archived.
    writeFile file: "output/usefulfile.txt", text: "This file is useful, need to archive it."

    // Write an useless file, which is not needed to be archived.
    writeFile file: "output/uselessfile.md", text: "This file is useless, no need to archive it."

    stage "Archive build output"

    // Archive the build output artifacts.
    archiveArtifacts artifacts: 'output/*.txt', excludes: 'output/*.md'
}

 **/
