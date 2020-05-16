parameters {
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
    }

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
