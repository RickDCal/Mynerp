/*
* Classe de utilidade para exibir lista de seleção a partir de uma célula editável do grid (com edição habilitada ou não)
* Para uso a partir de campo de nome em um form pode requerer adequação 
*/


Ext.define('Mynerp.util.GetIdNameField', {
    extend: 'Ext.form.field.Text',

    alias: 'widget.getidnamefield',

    //propriedades a serem sobrepostas e devem sempre ser fornecidas
    idFieldReference: null,  // exemplo: 'lookupNome' - será usado para saber onde colocar código desnecessário em caso de grid
    xtypeLista: null, /*OBRIGATORIO*/ // exemplo: 'lista-cliente-window', - será usado para saber qual janela possui a lista  
    codigoDataIndex: null, //o atributo do record que recebe o código quando se trata de grid. sem isso o campo de código não será alimentado 
    nomeDataIndex: null, //o atributo do record que recebe o nome quando se trata de grid. Sem isso o campo de nome não será alimentado. deve ser igual ao dataindex da coluna onde está o nome.
    chamador: 4, // ver possiveis valores na Mynerp.view.listaSelecao.base.WindowForm. default = 4, mudar no campo conforme a necessidade

    triggers: {
        buscar: {
            weight: 1,
            cls: Ext.baseCSSPrefix + 'form-search-trigger',
            handler: 'onGetIdNameTriggerClick',//'onSearchClick',
            //scope: 'this'
            field: this
        },
        limpar: {
            weight: 0,
            cls: Ext.baseCSSPrefix + 'form-clear-trigger',
            //hidden: false,
            handler: 'onGetIdNameClearClick',
            //scope: 
            field: this
        }
    }//,

    // listeners: {
    //     specialKey: 'onGetIdFieldEnter',
    //     blur: 'onGetIdFieldBlur' //perda do foco
    // }    
    
});