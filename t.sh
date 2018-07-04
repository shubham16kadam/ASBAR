ps aux  | awk '{print $11,$2}' | grep '\./[a-zA-Z]' | awk '{print $2}'
