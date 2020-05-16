properties([
        [$class: 'BuildDiscarderProperty', strategy: [$class: 'LogRotator', numToKeepStr: '500']],
        //pipelineTriggers([cron(env.BRANCH_NAME == 'master' ? 'H H(0-2) * * *' : '')]),
        parameters([
                string(name: 'LIB_VERSION', defaultValue: 'release/1.0', description: 'You can input the Jenkins shared library version, the repository link: https://bitbucket.telenav.com/scm/sear/jenkins-shared-libs.git'),
                choice(name: 'BUILD_TYPE', choices: 'CI\nDEV\nRC', description: 'build type'),
                booleanParam(name: 'FORCE_BUILD', defaultValue: false, description: 'Force build the app and service or not.'),
                booleanParam(name: 'REFRESH_BUILD', defaultValue: false, description: 'Do NOT do anything only refresh current script and skip all steps.'),
                text(name: 'DESCRIPTION', defaultValue: 'N/A', description: 'Any descriptions showing in release note'),
                choice(name: 'GRADLE_LOG_LEVEL', choices: ['stacktrace', 'info'],description: 'Gradle log level')
                extendedChoice( 
        defaultValue: 'One,Two,Three,Four', 
        description: '', 
        multiSelectDelimiter: ',', 
        name: 'SAMPLE_EXTENDED_CHOICE', 
        quoteValue: false, 
        saveJSONParameterToFile: false, 
        type: 'PT_CHECKBOX', 
        value:'One,Two,Three,Four,Five,Six,Seven,Eight,Nine,Ten', 
        visibleItemCount: 10)
        ])
])




// This shows a simple example of how to archive the build output artifacts.
node {
    stage "Create build output"
    
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
