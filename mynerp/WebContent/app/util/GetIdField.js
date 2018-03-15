/*
* Classe de utilidade para exibir lista de seleção a partir de um campo de código em um form
* Para uso em grid utilize outro componente já criado para esta finalidade
* Para uso a partir de campo de nome utilize outro componente já criado para esta finalidade 
*/

Ext.define('Mynerp.util.GetIdField', {
    extend: 'Ext.form.field.Text',

    alias: 'widget.getidfield',

    //propriedades a serem sobrepostas e devem sempre ser fornecidas
    modelName: null, // exemplo: Mynerp.model.cadastro.Cliente, sem aspas - será usado ao teclar enter
    nameFieldReference: null, // exemplo: 'lookupNome' - será usado para saber onde colocar o nome
    xtypeLista: null, // exemplo: 'lista-cliente-window', - será usado para saber qual janela possui a lista   
    chamador: 1, // ver possiveis valores na Mynerp.view.listaSelecao.base.WindowForm. default 1, mudar no campo conforme o uso

    triggers: {
        buscar: {
            weight: 1,
            cls: Ext.baseCSSPrefix + 'form-search-trigger',
            handler: 'onGetIdTriggerClick',//'onSearchClick',
            //scope: 'this'
            field: this
        }
    },

    listeners: {
        specialKey: 'onGetIdFieldEnter',
        blur: 'onGetIdFieldBlur' //perda do foco
    }    
    
});