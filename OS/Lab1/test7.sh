# ! /bin/bash

read -p  "Enter file1 : " file1
read -p "Enter file2 : "  file2

if ['diff $file1 $file2 >/dev/null:' -eq 0]
then
	echo same
else
	echo Different
	rm -f $file2
fi
