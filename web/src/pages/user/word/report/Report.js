import React, {Component} from 'react'
import {Row, Col, Card, Table} from 'antd'
import {Chart, Axis, Tooltip, Geom, Legend, Label, Coord} from 'bizcharts'
import {DataSet} from '@antv/data-set'
import Panel from '@components/Panel'
import fetch from '@common/fetch'

export default class Report extends Component {
  constructor(props) {
    super(props)
    this.state = {
      wordCountData: [],
      timeData: [],
      testData: []
    }
  }

  componentWillMount() {
    fetch({
      url: '/api/Ajax/AjaxGetVocStudyResult',
      method: 'get',
      type: 'json'
    }).then(result => {
      let data = (result.data||[]).map(item => {
        return {
          date: item.date.substr(5),
          voccount: item.voccount
        }
      })
      this.setState({
        wordCountData: data
      })
    })

    fetch({
      url: '/api/Ajax/AjaxGetMonthVocStudyResult',
      method: 'get',
      type: 'json'
    }).then(result => {
      let data = [
        {
          item: '单词识别',
          value: result.data[0].totalReadTimes
        },
        {
          item: '单词拼写',
          value: result.data[0].totalSpellTimes
        }
      ]
      this.setState({
        timeData: data
      })
    })

    fetch({
      url: '/api/Ajax/AjaxGettestResult',
      method: 'get',
      type: 'json'
    }).then(result => {
      this.setState({
        testData: result.data || []
      })
    })
  }

  render() {
    const wordCountCols = {
      date: {
        range: [0, 1]
      }
    }
    const wordCountTooltip = ['date*voccount', (date, voccount) => {
      return {
        name: '单词数',
        value: voccount
      }
    }]

    const timeData = new (DataSet.DataView)()
    timeData.source(this.state.timeData).transform({
      type: 'percent',
      field: 'value',
      dimension: 'item',
      as: 'percent'
    })
    const timeCols = {
      percent: {
        formatter: val => {
          return (val * 100).toFixed(2) + '%'
        }
      }
    }
    const columns = [
      {
        title: '序号',
        dataIndex: 'xuhao',
        key: 'xuhao',
        render: (text, record, index) => {
          return <span>{index+1}</span>
        }
      },
      {
        title: '课本',
        dataIndex: 'bookName',
        key: 'bookName'
      },
      {
        title: '测试类型',
        dataIndex: 'testType',
        key: 'testType',
        render: (text, record) => {
          if(text == 1) {
            return '单元测试'
          }
          if(text == 5) {
            return '自主测试'
          }
        }
      },
      {
        title: '单元',
        dataIndex: 'unitNbr',
        key: 'unitNbr',
        render: (text) => {
          if(!text || text == 0) {
            return '-'
          }
          return text
        }
      },
      {
        title: '分数',
        dataIndex: 'resultScore',
        key: 'resultScore',
        render: (text) => {
          if(text == 100) {
            return <span style={{color: 'red'}}>100</span>
          }
          return text
        }
      },
      {
        title: '通过',
        dataIndex: 'tongguo',
        key: 'tongguo',
        render: (text, record) => {
          let color = 'rgb(0, 128, 0)'
          let pass = '是'
          if(record.resultScore < 100 && record.resultScore >= 90) {
            color = 'rgb(255, 165, 0)'
          } else if(record.resultScore < 90) {
            color = 'rgb(255, 0, 0)'
            pass = '否'
          }
          return <span style={{color: color}}>{pass}</span>
        }
      }
    ]
    return (
      <div className='report'>
        <Panel title='学习报告'>
          <Row gutter={16}>
            <Col span={12}>
              <Card title='最近一周所学单词' hoverable>
                <Chart height={320} forceFit data={this.state.wordCountData} scale={wordCountCols}>
                  <Axis name='date'></Axis>
                  <Axis name='voccount'></Axis>
                  <Tooltip crosshairs={{type: 'y'}}></Tooltip>
                  <Geom tooltip={wordCountTooltip} type='interval' color='date' position='date*voccount'>
                    <Label content='voccount'></Label>
                  </Geom>
                  <Geom tooltip={false} type='line' position='date*voccount'></Geom>
                  <Geom tooltip={wordCountTooltip} type='point' position='date*voccount'></Geom>
                </Chart>
              </Card>
            </Col>
            <Col span={12}>
              <Card title='本月学习时间统计' hoverable>
                <Chart height={320} forceFit data={timeData} scale={timeCols}>
                  <Coord type='theta' radius={0.75}></Coord>
                  <Axis name='percent'></Axis>
                  <Geom type='intervalStack' position='percent' color='item'>
                    <Label content='percent' formatter={(val, item) => {
                      return item.point.item + ':' + val
                    }}></Label>
                  </Geom>
                </Chart>
              </Card>
            </Col>
          </Row>
          <Card title='所有测试成绩' hoverable style={{marginTop: '20px'}}>
            <Table
              rowKey='createtime'
              size='middle'
              pagination={false}
              dataSource={this.state.testData}
              columns={columns}
              footer={() => { return <div style={{textAlign: 'right'}}><a href='/#/user/test/results'>更多...</a></div>}}
              >
            </Table>
          </Card>
        </Panel>
      </div>
    )
  }
}