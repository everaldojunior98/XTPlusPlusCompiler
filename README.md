# XT++ Lexer
Projeto de um analisador léxico na disciplina de compiladores da Faculdade UCL.

### Exemplo:
#### Input:
```
#DECLARACAO
    val#INTEIRO
    res#INTEIRO
#ALGORITMO
    $comentario(s)
    LER val
    ARMAZENAR val EM res
    SE val >= 0 ENTAO
        ARMAZENAR 1 EM res
        ENQUANTO val > 1
        INIB
        ARMAZENAR res * (val - 1) EM res
        ARMAZENAR (val - 1,1) EM val
        FIMB
    FIM
```

#### Output:
```
<CERQUILHA,"#">
<DECLARACAO,"DECLARACAO">
<VARIAVEL,"val">
<CERQUILHA,"#">
<INTEIRO,"INTEIRO">
<VARIAVEL,"res">
<CERQUILHA,"#">
<INTEIRO,"INTEIRO">
<CERQUILHA,"#">
<ALGORITMO,"ALGORITMO">
<LER,"LER">
<VARIAVEL,"val">
<ARMAZENAR,"ARMAZENAR">
<VARIAVEL,"val">
<EM,"EM">
<VARIAVEL,"res">
<SE,"SE">
<VARIAVEL,"val">
<MAIOR_IGUAL,">=">
<IGUAL,"=">
<NUMERO_INTEIRO,"0">
<ENTAO,"ENTAO">
<ARMAZENAR,"ARMAZENAR">
<NUMERO_INTEIRO,"1">
<EM,"EM">
<VARIAVEL,"res">
<ENQUANTO,"ENQUANTO">
<VARIAVEL,"val">
<MAIOR,">">
<NUMERO_INTEIRO,"1">
<INICIOB,"INIB">
<ARMAZENAR,"ARMAZENAR">
<VARIAVEL,"res">
<MULTIPLICACAO,"*">
<ABRIR_PARENTESES,"(">
<VARIAVEL,"val">
<SUBTRACAO,"-">
<NUMERO_INTEIRO,"1">
<FECHAR_PARENTESES,")">
<EM,"EM">
<VARIAVEL,"res">
<ARMAZENAR,"ARMAZENAR">
<ABRIR_PARENTESES,"(">
<VARIAVEL,"val">
<SUBTRACAO,"-">
<NUMERO_REAL,"1,1">
<FECHAR_PARENTESES,")">
<EM,"EM">
<VARIAVEL,"val">
<FINALB,"FIMB">
<FIM,"FIM">
```
