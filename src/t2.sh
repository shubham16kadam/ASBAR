ps -ef | grep '\./[a-zA-Z][0-9]' | awk '{print $9}'
