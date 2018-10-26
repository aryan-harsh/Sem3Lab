# / bin/bash

read -p "Enter filename : " filename
read -p "Enter word : " word

grep  $word $filename -o | wc -l