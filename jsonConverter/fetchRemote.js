const http = require('http')
const fs = require('fs')
const url = process.argv[2] || 'http://data.wien.gv.at/daten/geo?service=WFS&request=GetFeature&version=1.1.0&typeName=ogdwien:AKUSTISCHEAMPELOGD&srsName=EPSG:4326&outputFormat=json'

http.get(url, (reply) => {
  const statusCode = reply.statusCode
  const contentType = reply.headers['content-type']

  let error
  if (statusCode !== 200) {
    error = new Error(`Request Failed.\n` + `Status Code: ${statusCode}`)
  } else if (!/^application\/json/.test(contentType)) {
    error = new Error(`Invalid content-type.\n` + `Expected application/json but received ${contentType}`)
  }
  if (error) {
    console.log(error.message)
    return
  }

  reply.setEncoding('utf8');
  let data = ''
  reply.on('data', (chunk) => data += chunk)
  reply.on('end', () => {
    try {
			fs.writeFile('AKUSTISCHEAMPELOGD.json', data, (err) => {
				if (err) throw err;
			})
    } catch (e) {
      console.log(e.message)
    }
  });
}).on('error', (e) => {
  console.log(`Got error: ${e.message}`)
})
