/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TextInput,
  Linking,
  Navigator
} from 'react-native';

import Button from 'react-native-button';

import EditPlace from './EditPlace';
import PlacesList from './PlacesList';;
import Root from './root';


export default class TravelAppREACT extends Component {

	renderScene(route, navigator) {
		
		if(route.name == 'root') {
			return <Root navigator={navigator} />
		}
		if(route.name == 'placesList') {
			return <PlacesList navigator={navigator} />
		}
		if(route.name == 'editPlace') {
        		return <EditPlace navigator={navigator} place={route.data}/>
     		}
	}

	render() {
	      return (
		<View style={styles.container}>
			<Navigator
			initialRoute={{name: 'root' }}
			renderScene={this.renderScene.bind(this)}
			style={{padding: 100}}
			/>
      		</View>
	      );
        }
}

const styles = StyleSheet.create({
container: {
    flex: 1,
    backgroundColor: '#F5FCFF',
  },
});

AppRegistry.registerComponent('TravelAppREACT', () => TravelAppREACT);
