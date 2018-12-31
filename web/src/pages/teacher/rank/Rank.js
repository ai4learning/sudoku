import React, { Component } from 'react'
import { Tabs } from 'antd'
import { Chart, Axis, Tooltip, Geom, Legend, Label, Coord } from 'bizcharts'
import Panel from '@components/Panel'
import fetch from '@common/fetch'

export default class Rank extends Component {
  constructor(props) {
    super(props)

    this.state = {
      currentDayStatistics: {},
      cumulativeStatistics: {}
    }
  }

  componentWillMount() {
    this.getCurrentDayStatistics()
    this.getCumulativeStatistics()
  }

  getCurrentDayStatistics() {
    fetch({
      url: '/api/teacher/AjaxGetRealTimeSituationTeacher',
      method: 'get',
      type: 'json',
      data: {
        classNO: this.props.location.query.classNo
      }
    }).then(data => {
      this.setState({
        currentDayStatistics: data
      })
    })
  }

  getCumulativeStatistics() {
    fetch({
      url: '/api/teacher/AjaxGetCumulativeSituationTeacher',
      method: 'get',
      type: 'json',
      data: {
        classNO: this.props.location.query.classNo
      }
    }).then(data => {
      this.setState({
        cumulativeStatistics: data
      })
    })
  }

  render() {
    const currentDayCols = {
    }
    const currentDayTooltip = ['name*achievement', (name, achievement) => {
      return {
        name: '单词数',
        value: achievement
      }
    }]
    const cumulativeCols = {
    }
    const cumulativeToolTip = ['name*achievement', (name, achievement) => {
      return {
        name: '单词数',
        value: achievement
      }
    }]
    return (
      <div class='page_rank'>
        <Tabs tabPosition='left' size='large'>
          <Tabs.TabPane key='current' tab='实时战况'>
            <Panel title='实时战况'>
              <Chart height={400} padding={[30, 30, 80, 80]} forceFit data={this.state.currentDayStatistics.achievements || []} scale={currentDayCols}>
                <Axis name='name'></Axis>
                <Axis name='achievement'></Axis>
                <Tooltip crosshairs={{ type: 'y' }}></Tooltip>
                <Geom tooltip={currentDayTooltip} type='interval' color='achievement' position='name*achievement'>
                  <Label content='achievement'></Label>
                </Geom>
              </Chart>
            </Panel>
          </Tabs.TabPane>
          <Tabs.TabPane key='cumulative' tab='累计战况'>
            <Panel title='累计战况'>
              <Chart height={400} padding={[30, 30, 80, 80]} forceFit data={this.state.cumulativeStatistics.achievements || []} scale={cumulativeCols}>
                <Axis name='name'></Axis>
                <Axis name='achievement'></Axis>
                <Tooltip crosshairs={{ type: 'y' }}></Tooltip>
                <Geom tooltip={cumulativeToolTip} type='interval' color='achievement' position='name*achievement'>
                  <Label content='achievement'></Label>
                </Geom>
              </Chart>
            </Panel>
          </Tabs.TabPane>
        </Tabs>
      </div>
    )
  }
}