Ext.define('Mynerp.view.base.CancelSaveToolbar', {
    extend: 'Ext.toolbar.Toolbar',
    xtype: 'cancel-save-toolbar',

    requires: [
        'Mynerp.util.Glyphs'
    ],

    dock: 'bottom',
    ui: 'footer',
    layout: {
        pack: 'end',
        type: 'hbox'
    },
    items: [
        {
            xtype: 'button',
            text: 'Save',
            glyph: Mynerp.util.Glyphs.getGlyph('save'),
            listeners: {
                click: 'onSave'
            }
        },
        {
            xtype: 'button',
            text: 'Cancel',
            glyph: Mynerp.util.Glyphs.getGlyph('cancel'),
            listeners: {
                click: 'onCancel'
            }
        }
    ]
});