properties([
        [$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '500']],
        //pipelineTriggers([cron(env.BRANCH_NAME == 'master' ? 'H H(0-2) * * *' : '')]),
        parameters([
                string(name: 'LIB_VERSION', defaultValue: 'release/1.0', description: 'You can input the Jenkins shared library version, the repository link: https://bitbucket.telenav.com/scm/sear/jenkins-shared-libs.git'),
                choice(name: 'BUILD_TYPE', choices: 'CI\nDEV\nRC', description: 'build type'),
                booleanParam(name: 'FORCE_BUILD', defaultValue: false, description: 'Force build the app and service or not.'),
                booleanParam(name: 'REFRESH_BUILD', defaultValue: false, description: 'Do NOT do anything only refresh current script and skip all steps.'),
                text(name: 'DESCRIPTION', defaultValue: 'N/A', description: 'Any descriptions showing in release note'),
                choice(name: 'GRADLE_LOG_LEVEL', choices: ['stacktrace', 'info'],description: 'Gradle log level'),
		extendedChoice( 
            name: 'TagName', 
            defaultValue: '', 
            description: 'tag name', 
            type: 'PT_SINGLE_SELECT', 
            groovyScript: """def gettags = ("git ls-remote -t https://github.com/tomerb3/supremedevops.git").execute()
               return gettags.text.readLines().collect { it.split()[1].replaceAll('refs/tags/', '').replaceAll("\\\\^\\\\{\\\\}", '')}
                          """,),
		/**
		extendedChoice( 
            name: 'ALL OPTIONS', 
            groovyScript: """import org.boon.Boon;

def jsonEditorOptions = Boon.fromJson(/{

        disable_edit_json: true,

        disable_properties: false,

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

        "BUILD_TYPE"

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

        "LIB_VERSION": {

            "type": "string",

            "description": "You can input the Jenkins shared library version, the repository link: https:\/\/bitbucket.telenav.com\/scm\/sear\/jenkins-shared-libs.git",

            "default": "release\/1.0"

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

        "FORCE_BUILD": {

            "type": "boolean",

            "format": "checkbox",

            "description": "Force build the app and service or not."

        },

        "REFRESH_BUILD": {

            "type": "boolean",

            "format": "checkbox",

            "description": "Do NOT do anything only refresh current script and skip all steps."

        },

        "releaseNotes": {

            "title": "Release Notes",

            "type": "object",

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

        "onboard": {

            "title": "Onboard Settings",

            "type": "object",

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

        "cloud": {

            "title": "Cloud Settings",

            "type": "object",

            "properties": {

                "CLOUD_BUILD_PRODUCT": {

                    "type": "string",

                    "description": "build product",

                    "enum": [

                        "Denali",

                        "Sync4",

                        "Gen3"

                    ]

                },

                "CLOUD_BUILD_REGIONS": {

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

                "CLOUD_DATA_LOCATION": {

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

            "default": "N\/A"

        }

    }

},

       startval: {

         color :"red"

       }

}/);
                       return jsonEditorOptions;
                          """,)
			  
			  
			  **/
])
])

//import org.boon.Boon;
/**
def jsonEditorOptions = Boon.fromJson(/{
		disable_edit_json: true,
        disable_properties: true,
        no_additional_properties: true,
        disable_collapse: true,
        disable_array_add: true,
        disable_array_delete: true,
        disable_array_reorder: true,
        theme: "bootstrap2",
        iconlib:"fontawesome4",
		schema: {
		  "type": "object",
		  "title": "Name",
		  "properties": {
			"first_name": {
			  "type": "string",
			  "propertyOrder" : 1
			},
			"last_name": {
			  "type": "string",
			  "propertyOrder" : 2
			},
			"full_name": {
			  "type": "string",
			  "propertyOrder" : 3,
			  "template": "{{fname}} {{lname}}",
			  "watch": {
				"fname": "first_name",
				"lname": "last_name"
			  }
			}
		  }
		},
		startval: {
			"first_name" : "John",
			"last_name" : "Doe",
			"full_name" : "John Doe"
		}
}/);

**/

// This shows a simple example of how to archive the build output artifacts.
node {
    
    
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
