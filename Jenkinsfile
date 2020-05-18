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
                extendedChoice bindings: '', description: '', groovyClasspath: '', groovyScript: '''

  import org.boon.Boon;

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

          "title": "Color Picker",

          "type": "object",

          "properties": {

            "color": {

              "type": "string",

              "format": "color"

            }

          }

        },

        startval: {

            color :"red"

        }}/);

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
