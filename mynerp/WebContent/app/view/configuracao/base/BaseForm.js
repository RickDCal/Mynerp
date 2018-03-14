Ext.define('Mynerp.view.configuracao.base.BaseForm', {
	extend: 'Ext.form.Panel',

	xtype: 'config-base-form',

	requires: [
		'Mynerp.util.Util',
		'Mynerp.util.Glyphs',
		'Mynerp.view.base.CancelSaveToolbar'
	],

	autoScroll: true,
	layout: {
		type: 'fit'
	},

	closable: false,

	dockedItems: [
		{
			xtype: 'toolbar',
			dock: 'top',
			layout: {
        		pack: 'start',
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
		}
	]
});