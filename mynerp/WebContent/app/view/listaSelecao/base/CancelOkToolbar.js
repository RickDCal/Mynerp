Ext.define('Mynerp.view.listaSelecao.base.CancelOkToolbar', {
    extend: 'Ext.toolbar.Toolbar',
    xtype: 'cancel-ok-toolbar',

    requires: [
        'Mynerp.util.Glyphs'
    ],

    controller: 'listaselecao',

    dock: 'bottom',
    ui: 'footer',
    layout: {
        pack: 'end',
        type: 'hbox'
    },
    items: [
        {
            xtype: 'button',
            text: 'OK',
            glyph: Mynerp.util.Glyphs.getGlyph('save'),
            listeners: {
                click: 'onListaSelecaoConfirm'
            }
        },
        {
            xtype: 'button',
            text: 'Cancel',
            glyph: Mynerp.util.Glyphs.getGlyph('cancel'),
            listeners: {
                click: 'onListaSelecaoCancel'
            }
        }
    ]
});