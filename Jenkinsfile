properties([
        [$class: 'BuildDiscarderProperty', strategy:[$class: 'LogRotator', numToKeepStr: '200']],
        //pipelineTriggers([cron(env.BRANCH_NAME == 'master' ? 'H */3 * * *' : '')]),
        //pipelineTriggers([cron(env.BRANCH_NAME == 'master' ? 'H H(0-2) * * *' : '')]),
        parameters([
                string(name: 'LIB_VERSION', defaultValue: 'release/1.0', description: 'You can input the Jenkins shared library version, default is 0.1.0, the repository link: https://bitbucket.telenav.com/projects/SEAR/repos/jenkinsutils/browse'),
                choice(name: 'BUILD_TYPE', choices: 'CI\nDEV\nRC', description: 'Build type'),
                booleanParam(name: 'FORCE_BUILD', defaultValue: false, description: 'Force build the app and service or not.'),
                booleanParam(name: 'REFRESH_BUILD', defaultValue: false, description: 'Do NOT do anything only refresh current script and skip all steps.'),
                text(name: 'DESCRIPTION', defaultValue: 'N/A', description: 'Any descriptions showing in release note'),
                /**
                String fileContents = new File('/path/to/file').getText('UTF-8')

                **/
                extendedChoice (bindings: '', description: '', groovyClasspath: '', groovyScript: '''
                import org.boon.Boon;
                println("VINEEL_TESTING_TESTING");
                def data = new File('data_from_eric.json').getText('UTF-8');
                def jsonEditorOptions = Boon.fromJson('${data}');
                return jsonEditorOptions;
                ''', multiSelectDelimiter: ',', name: 'Policy12345', quoteValue: false, saveJSONParameterToFile: false, type: 'PT_JSON', visibleItemCount: 5),
                extendedChoice( defaultValue: 'One,Two,Three,Four',
                                   description: '',
                                   javascriptFile: 'test_data.json',
                                   multiSelectDelimiter: ',',
                                   name: 'SAMPLE_EXTENDED_CHOICE',
                                   quoteValue: false,
                                   saveJSONParameterToFile:  true,
                                   type: 'PT_JSON',
                                   value:'One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten',
                                   visibleItemCount: 7),
                 extendedChoice (bindings: '', description: '', groovyClasspath: '', groovyScript: '''
                import org.boon.Boon;

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
