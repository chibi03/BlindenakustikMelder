const Ampeljson = require('./AKUSTISCHEAMPELOGD.json').features
const newJson = {}

const _id = 1
const coord = {}

Ampeljson.map(entry => coord.x = entry.geometry.coordinates[0])

console.log(coord)
