const local = require('./AKUSTISCHEAMPELOGD.json').features
const opts = require('./.convOpts.json')

const newO = []

local.map((e,i) => newO.push({
	_id: Number.parseInt(e.id.match(/\d+/g)[0]),
	coordinates: {
		x: e.geometry.coordinates[0],
		y: e.geometry.coordinates[1]
	},
	crossing: e.properties.KREUZUNG,
	district: e.properties.BEZIRK,
	_version: opts.version || 1,
	//oldID: e.id,
	//trafficLightNumbers: []
}))

module.exports = newO
