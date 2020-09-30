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
<D,"#">
<DEC,"DECLARACAO">
<V,"val">
<D,"#">
<INT,"INTEIRO">   
<V,"res">
<D,"#">
<INT,"INTEIRO">   
<D,"#">
<ALG,"ALGORITMO"> 
<LER,"LER">       
<V,"val">
<ARM,"ARMAZENAR"> 
<V,"val">
<EM,"EM">
<V,"res">
<SE,"SE">
<V,"val">
<OR,">=">
<Ni,"0">
<ENT,"ENTAO">
<ARM,"ARMAZENAR">
<Ni,"1">
<EM,"EM">
<V,"res">
<ENQ,"ENQUANTO">
<V,"val">
<OR,">">
<Ni,"1">
<INIB,"INIB">
<ARM,"ARMAZENAR">
<V,"res">
<OA,"*">
<Ap,"(">
<V,"val">
<OA,"-">
<Ni,"1">
<Fp,")">
<EM,"EM">
<V,"res">
<ARM,"ARMAZENAR">
<Ap,"(">
<V,"val">
<OA,"-">
<Nd,"1,1">
<Fp,")">
<EM,"EM">
<V,"val">
<FIMB,"FIMB">
<FIM,"FIM">
```
