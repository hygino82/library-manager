Erro de cache no Git

o Git não mostrava a pasta porque ela estava ignorada ou cacheada de forma invisível;

ao executar git rm -r --cached angular-frontend, forçamos ele a reconsiderar;

o git add voltou a funcionar e os arquivos apareceram no git status.
