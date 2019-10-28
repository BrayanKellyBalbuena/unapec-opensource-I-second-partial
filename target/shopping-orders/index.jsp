<%--
  Created by IntelliJ IDEA.
  User: scrip
  Date: 9/15/2019
  Time: 2:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericpage>
  <jsp:attribute name="head"><Title>Index</Title></jsp:attribute>
  <jsp:attribute name="otherScripst">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
    <script src="js/index.js"></script>

  </jsp:attribute>
  <jsp:body>
    <template v-if="currentUser.email == 'b@b.com'">
      <v-row class="mt-4 ml-10">
        <v-col cols="4">
          <v-card @click="showReportUser"
                  max-width="400"
          >
            <v-img
                    class="white--text align-end"
                    height="200px"
                    src="https://www.irishtimes.com/polopoly_fs/1.2459946.1449660348!/image/image.jpg"
            >
            </v-img>
            <v-card-title>
              Total Order Report by User
            </v-card-title>


          </v-card>
        </v-col>

        <v-col cols="4">
          <v-card
                  max-width="400"
                  class="mx-auto"
          >
            <v-img
                    class="white--text align-end"
                    height="200px"
                    src="https://www.clipartmax.com/png/middle/180-1804535_customized-delivery-boy.png"
            >
            </v-img>
            <v-card-title>
              Order by date
            </v-card-title>

          </v-card>
        </v-col>

      </v-row>
      <v-dialog v-model="dialog" max-width="1080px">
        <v-card>
          <v-card-title>
            <span class="headline">Report Top buyer customers</span>
          </v-card-title>

          <v-card-text>
            <canvas id="popChart" height="300" ref="canvas"></canvas>

          </v-card-text>

          <v-card-actions>
            <div class="flex-grow-1"></div>


          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>
  </jsp:body>
</t:genericpage>