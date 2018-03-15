Ext.define('Mynerp.util.Glyphs', {
	singleton: true,

	//versão do fontawessome inicialmente é a 4.6.3 -- manter anotado quando atualizar.

	config: { //pq esta ´recisou de config aqui dentro explicito?

		//webFont: 'FontAwesome',
		// colocar em ordem alfabetica
		add: 'xf067',
		cancel: 'xf0e2',
		clearFilter: 'xf0b0',
		destroy: 'xf1f8',
		edit: 'xf040',
		listaNum: 'xf0cb',
		listaPonto: 'xf0ca',
		lupa: 'xf002',
		refresh: 'xf021',
		save: 'xf00c',		
		saveAll: 'xf0c7'
		
	},

	constructor: function(config) {
		this.initConfig(config);
	},

	getGlyph: function(glyph) {

		var me = this,
		font = 'FontAwesome'; //me.getWebFont();

		if(typeof me.config[glyph] === 'undefined') {
			return false;
		}

		return me.config[glyph] + '@' + font;
		
	}
});