# XT++ Compiler
Projeto de um compilador desenvolvido na disciplina de compiladores da Faculdade UCL.

## Parte 1: Analisador léxico
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

## Parte 2: Analisador Sintático
### Exemplo:
#### Input:
```
#DECLARACAO
n1#INTEIRO
n2#INTEIRO
auxiliar#INTEIRO

#ALGORITMO
$ Trocando valores entre 2 vari�veis e atribuindo valores.
LER n1
LER n2
SE n1 > n2 ENTAO
    INIB
        ARMAZENAR n1 EM auxiliar
        ARMAZENAR n2 EM n1
        ARMAZENAR auxiliar EM n2
        ARMAZENAR 2+2*2-2+2 EM auxiliar
    FIMB
SE n2 > n1 ENTAO
    INIB
        ARMAZENAR n2 EM auxiliar
        ARMAZENAR n1 EM n2
        ARMAZENAR auxiliar EM n1
        ARMAZENAR 2+2*2-2+2 EM auxiliar
    FIMB
ESCREVER n1
ESCREVER n2
ESCREVER auxiliar
```

#### Output:
```
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Lido:  <DL,"#">
Combinação: <DL,"#">
Lido:  <DEC,"DECLARACAO">     
Combinação: <DEC,"DECLARACAO">
Lido:  <V,"n1">
Combinação: <V,"n1">
Lido:  <DL,"#">
Combinação: <DL,"#">
Lido:  <INT,"INTEIRO">        
Combinação: <INT,"INTEIRO">   
Lido:  <V,"n2">
Combinação: <V,"n2">
Lido:  <DL,"#">
Combinação: <DL,"#">
Lido:  <INT,"INTEIRO">        
Combinação: <INT,"INTEIRO">
Lido:  <V,"auxiliar">
Combinação: <V,"auxiliar">
Lido:  <DL,"#">
Combinação: <DL,"#">
Lido:  <INT,"INTEIRO">
Combinação: <INT,"INTEIRO">
Lido:  <DL,"#">
Combinação: <DL,"#">
Lido:  <ALG,"ALGORITMO">
Combinação: <ALG,"ALGORITMO">
Lido:  <LER,"LER">
Combinação: <LER,"LER">
Lido:  <V,"n1">
Combinação: <V,"n1">
Lido:  <LER,"LER">
Combinação: <LER,"LER">
Lido:  <V,"n2">
Combinação: <V,"n2">
Lido:  <SE,"SE">
Combinação: <SE,"SE">
Lido:  <V,"n1">
Combinação: <V,"n1">
Lido:  <OR_MAIOR,">">
Combinação: <OR_MAIOR,">">
Lido:  <V,"n2">
Combinação: <V,"n2">
Lido:  <ENT,"ENTAO">
Combinação: <ENT,"ENTAO">
Lido:  <INIB,"INIB">
Combinação: <INIB,"INIB">
Lido:  <ARM,"ARMAZENAR">
Combinação: <ARM,"ARMAZENAR">
Lido:  <V,"n1">
Combinação: <V,"n1">
Lido:  <EM,"EM">
Combinação: <EM,"EM">
Lido:  <V,"auxiliar">
Combinação: <V,"auxiliar">
Lido:  <ARM,"ARMAZENAR">
Combinação: <ARM,"ARMAZENAR">
Lido:  <V,"n2">
Combinação: <V,"n2">
Lido:  <EM,"EM">
Combinação: <EM,"EM">
Lido:  <V,"n1">
Combinação: <V,"n1">
Lido:  <ARM,"ARMAZENAR">
Combinação: <ARM,"ARMAZENAR">
Lido:  <V,"auxiliar">
Combinação: <V,"auxiliar">
Lido:  <EM,"EM">
Combinação: <EM,"EM">
Lido:  <V,"n2">
Combinação: <V,"n2">
Lido:  <ARM,"ARMAZENAR">
Combinação: <ARM,"ARMAZENAR">
Lido:  <Ni,"2">
Combinação: <Ni,"2">
Lido:  <OASo,"+">
Combinação: <OASo,"+">
Lido:  <Ni,"2">
Combinação: <Ni,"2">
Lido:  <OAMult,"*">
Combinação: <OAMult,"*">
Lido:  <Ni,"2">
Combinação: <Ni,"2">
Lido:  <OASub,"-">
Combinação: <OASub,"-">
Lido:  <Ni,"2">
Combinação: <Ni,"2">
Lido:  <OASo,"+">
Combinação: <OASo,"+">
Lido:  <Ni,"2">
Combinação: <Ni,"2">
Lido:  <EM,"EM">
Combinação: <EM,"EM">
Lido:  <V,"auxiliar">
Combinação: <V,"auxiliar">
Lido:  <FIMB,"FIMB">
Combinação: <FIMB,"FIMB">
Lido:  <SE,"SE">
Combinação: <SE,"SE">
Lido:  <V,"n2">
Combinação: <V,"n2">
Lido:  <OR_MAIOR,">">
Combinação: <OR_MAIOR,">">
Lido:  <V,"n1">
Combinação: <V,"n1">
Lido:  <ENT,"ENTAO">
Combinação: <ENT,"ENTAO">
Lido:  <INIB,"INIB">
Combinação: <INIB,"INIB">
Lido:  <ARM,"ARMAZENAR">
Combinação: <ARM,"ARMAZENAR">
Lido:  <V,"n2">
Combinação: <V,"n2">
Lido:  <EM,"EM">
Combinação: <EM,"EM">
Lido:  <V,"auxiliar">
Combinação: <V,"auxiliar">
Lido:  <ARM,"ARMAZENAR">
Combinação: <ARM,"ARMAZENAR">
Lido:  <V,"n1">
Combinação: <V,"n1">
Lido:  <EM,"EM">
Combinação: <EM,"EM">
Lido:  <V,"n2">
Combinação: <V,"n2">
Lido:  <ARM,"ARMAZENAR">
Combinação: <ARM,"ARMAZENAR">
Lido:  <V,"auxiliar">
Combinação: <V,"auxiliar">
Lido:  <EM,"EM">
Combinação: <EM,"EM">
Lido:  <V,"n1">
Combinação: <V,"n1">
Lido:  <ARM,"ARMAZENAR">
Combinação: <ARM,"ARMAZENAR">
Lido:  <Ni,"2">
Combinação: <Ni,"2">
Lido:  <OASo,"+">
Combinação: <OASo,"+">
Lido:  <Ni,"2">
Combinação: <Ni,"2">
Lido:  <OAMult,"*">
Combinação: <OAMult,"*">
Lido:  <Ni,"2">
Combinação: <Ni,"2">
Lido:  <OASub,"-">
Combinação: <OASub,"-">
Lido:  <Ni,"2">
Combinação: <Ni,"2">
Lido:  <OASo,"+">
Combinação: <OASo,"+">
Combinação: <Ni,"2">
Combinação: <EM,"EM">
Combinação: <V,"auxiliar">
Combinação: <FIMB,"FIMB">
Combinação: <ESC,"ESCREVER">
Combinação: <V,"n1">
Combinação: <ESC,"ESCREVER">
Combinação: <V,"n2">
Combinação: <ESC,"ESCREVER">
Combinação: <V,"auxiliar">
```