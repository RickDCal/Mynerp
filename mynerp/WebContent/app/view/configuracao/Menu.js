Ext.define('Mynerp.view.configuracao.Menu', {
    extend: 'Ext.tree.Panel',
    xtype: 'config-menu',
    width: 250,

    title: 'Parâmetros', 
    rootVisible: false,
    useArrows: true,
    colspan: 2,
    split: true,
    collapsible: true,

    // a store será colocada com bind no listener da store
    // store: {
    //         type: 'menu-configuracoes'
    // },   

    autoLoad: true, 

    listeners: {
        itemClick: 'onTreeNodeClick'
    }

});