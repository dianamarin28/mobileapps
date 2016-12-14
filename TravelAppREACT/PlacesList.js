'use strict';
import React, { Component } from 'react';
import {
  StyleSheet,
  TextInput,
  TouchableHighlight,
  AsyncStorage,
  Text,
  View
} from 'react-native';

import Button from 'react-native-button';

class PlacesList extends Component {

	constructor(){
		super();
		this.list = {
			places: [
                {country: 'Romania', city: 'Sibiu', rating: 9},
                {country: 'Romania', city: 'Brasov', rating: 8},
                {country: 'France', city: 'Paris', rating: 10}
                ]
		}
	}

  	navigate(routeName, data) {
      		this.props.navigator.push({
        		name: routeName,
        		data: data
        	});
    	}

	redirect(routeName, accessToken){
		this.props.navigator.push({
			name: routeName
		});
	}

	render() {

		const l = this.list.places.map((data, index) => {
			return (
				<View key={index}>
					<Button onPress={ this.navigate.bind(this, 'editPlace', data) }>
					{data.country + ' ' + data.city + ' ' + data.rating}
					</Button>
				</View>
			)
		})


		return (
			<View style={styles.container}>
				<Text style={styles.heading}>Places:</Text>
				{l}
			</View>
		);
	}
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        backgroundColor: '#F5FCFF',
        padding: 10,
        paddingTop: 80
    },
    listContainer:{
        flex: 1,
        justifyContent: 'flex-start',
        backgroundColor: '#FFF',
        padding: 10,
        paddingTop: 80
    },
    heading: {
        textAlign: 'center',
        fontSize: 30,
        marginBottom: 15,
        fontWeight: 'bold',
        // color: '#bcb696'
    }
});

export default PlacesList
