#version 150

#moj_import <light.glsl>
#moj_import <fog.glsl>
//#include veil:camera

in vec3 Position;
in vec4 Color;
in vec2 UV0;
in vec2 UV1;
in vec2 UV2;
in vec3 Normal;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;
uniform float STime;
uniform vec3 ChunkOffset;
uniform int FogShape;
uniform vec3 CameraPos;
uniform mat4 IViewMat;


out vec4 vertexColor;
out vec2 texCoord0;
out vec2 texCoord1;
out vec2 texCoord2;
out vec4 normal;
out float vertexDistance;

void main() {

    //vec3 viewPos = Position + ChunkOffset;
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);

    vertexColor = Color;
    texCoord0 = UV0;
    texCoord1 = UV1;
    texCoord2 = UV2;
    normal = ProjMat * ModelViewMat * vec4(Normal, 0.0);
}