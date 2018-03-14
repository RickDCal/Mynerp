Ext.define('Mynerp.view.login.CapsLockTooltip', {
	
	extend: 'Ext.tip.QuickTip',
	
	xtype: 'capslocktooltip',
	
	target: 'password',	
	anchor: 'top',	
	anchorOffSet: 0,	
	width: 300,	
	dismissdelay: 0,
	autoHide: false,
	title: '<div class="fa fa-exclamation-triangle"></div><div>' + 'Caps Lock está ativada' + '</div>',
	html: '<div>'+ 'Caps Lock ativada porde fazer com que ' +
        'você introduza sua senha incorretamente.' + '</div><br/>' +
        '<div>'+ 'Você deve pressionar Caps Lock para ' +
        'desativá-la antes de inserir sua senha.' + '</div>'
});