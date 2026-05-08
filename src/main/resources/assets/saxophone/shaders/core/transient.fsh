#version 150



uniform sampler2D Sampler0;
uniform sampler2D Sampler1;
uniform sampler2D Sampler2;

uniform vec2 ScreenSize;
uniform float STime;
uniform float Randomizer;

in vec4 vertexColor;

//Use this one!
in vec2 texCoord0;
in vec2 texCoord1;
in vec2 texCoord2;
in vec4 normal;

out vec4 fragColor;


void main() {
    vec4 color = texture(Sampler0, texCoord0);
    if (color.a < vertexColor.a) {
        discard;
    }


    float greybit = (fragColor.r+fragColor.g+fragColor.b)/3;


    fragColor = texture(Sampler0,texCoord0);
    fragColor.a = 0.5F;
   // fragColor.a = ((fragColor.a*1.0F)+(fragColor.a*sin(STime)))/2.0F;

}