
git add .

# no new line
echo -n "> Commit message: "
# shellcheck disable=SC2162
read commit_message

if [ -z "$commit_message" ]; then
  git commit -m "None message"
else
  git commit -m "$commit_message}"
fi

git push -u origin master