Diferença entre Workflows e Actions

Workflow: É o processo automatizado configurável que executará um ou mais jobs. É definido por um arquivo YAML e disparado por eventos (push, manual, etc.).

Action: É uma aplicação personalizada para a plataforma GitHub Actions que executa uma tarefa complexa, mas repetitiva. É a unidade básica de um "step".

Estrutura de uma Action:
Internamente, uma action contém um arquivo action.yml que define suas inputs (parâmetros de entrada), outputs (resultados) e o ambiente de execução (Docker, JavaScript ou Composite).

Exemplo Prático:
No nosso projeto, utilizamos a action actions/setup-java@v4. Nós a chamamos através da instrução uses:, e passamos parâmetros como java-version e distribution através da chave with:.