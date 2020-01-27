#!/usr/bin/env bash
# this takes a confidential data bundle in zip format (generated by Google Drive when downloading the folder) and puts 
# content in the appropriate places in our build directories
# warning: do not commit this data accidentally!
set -o errexit
set -o pipefail
set -o nounset
set -o xtrace

# bash expand_confidential_bundle.sh /path/to/zip/archive folder_name
# ex. bash expand_confidential_bundle.sh /home/aduncan/Downloads/add_curator.zip add_curator

zipArchive=$1
zipFolder=$2

# hack
mkdir -p dockstore-webservice/src/main/resources/

unzip -p $zipArchive $zipFolder/config_file.txt >dockstore-cli-integration-testing/src/test/resources/config_file.txt
unzip -p $zipArchive $zipFolder/config_file2.txt >dockstore-cli-integration-testing/src/test/resources/config_file2.txt
unzip -p $zipArchive $zipFolder/migrations.test.confidential1.xml >dockstore-webservice/src/main/resources/migrations.test.confidential1.xml
unzip -p $zipArchive $zipFolder/migrations.test.confidential2.xml >dockstore-webservice/src/main/resources/migrations.test.confidential2.xml
unzip -p $zipArchive $zipFolder/migrations.test.confidential1_1.5.0.xml >dockstore-webservice/src/main/resources/migrations.test.confidential1_1.5.0.xml
unzip -p $zipArchive $zipFolder/migrations.test.confidential2_1.5.0.xml >dockstore-webservice/src/main/resources/migrations.test.confidential2_1.5.0.xml
unzip -p $zipArchive $zipFolder/dockstoreTest.yml >dockstore-cli-integration-testing/src/test/resources/dockstoreTest.yml
unzip -p $zipArchive $zipFolder/dstesting_pcks8.pem >dockstore-cli-integration-testing/src/test/resources/dstesting_pcks8.pem
